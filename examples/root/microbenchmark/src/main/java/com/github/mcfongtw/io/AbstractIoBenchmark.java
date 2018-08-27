package com.github.mcfongtw.io;

import com.codahale.metrics.ScheduledReporter;
import com.github.mcfongtw.utils.SudoExecutors;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.github.mcfongtw.io.AbstractIoBenchmark.AbstractRandomAccessExecutionPlan.DataType.*;

public abstract class AbstractIoBenchmark {

    protected static final int NUM_ITERATION = 100;

    private static final int UNIT_ONE_KILO = 1024;

    private static final int UNIT_ONE_MEGA = UNIT_ONE_KILO * UNIT_ONE_KILO;

    private static final int UNIT_ONE_GIGA = UNIT_ONE_KILO * UNIT_ONE_MEGA;

    protected static final int TOTAL_DATA_WRITEN = 1 * UNIT_ONE_MEGA;

    protected static ScheduledReporter metricReporter = InfluxdbReporterSingleton.newInstance();

    protected static abstract class AbstractExecutionPlan implements ExecutableLifecycle {
        protected Logger logger = LoggerFactory.getLogger(this.getClass());

        private String sudoPassword = "";


        public AbstractExecutionPlan() {
            logger.info("Starting reporting metric...");
            metricReporter.start(500, TimeUnit.MILLISECONDS);
            logger.info("Starting reporting metric...DONE");

            String sudoPasswordVal = System.getProperty("sudoPassword");
            if(StringUtils.isNotEmpty(sudoPasswordVal)) {
                sudoPassword = sudoPasswordVal;
            }
            logger.debug("SudoPassword: {}", sudoPassword);
        }

        @Override
        public void preTrialSetUp() throws Exception {
            logger.trace("[preTrialSetUp]");
        }

        @Override
        public void doTrialSetUp() throws Exception {
            this.preTrialSetUp();
            logger.trace("[doTrialSetUp]");
            this.postTrialSetUp();
        }

        @Override
        public void postTrialSetUp() throws IOException, InterruptedException {
            logger.trace("[postTrialSetUp]");

            if(StringUtils.isNotEmpty(sudoPassword)) {
                logger.info("Start to dropping free pagecache, dentries and inodes...");
                SudoExecutors.exec("echo 3 > /proc/sys/vm/drop_caches", sudoPassword);
                logger.info("Start to dropping free pagecache, dentries and inodes...DONE");
            }
        }

        @Override
        public void preTrialTearDown() throws Exception {
            logger.trace("[preTrialTearDown]");

        }

        @Override
        public void doTrialTearDown() throws Exception {
            this.preTrialTearDown();
            logger.trace("[doTrialTearDown]");
            this.postTrialTearDown();
        }

        @Override
        public void postTrialTearDown() throws Exception {
            logger.trace("[postTrialTearDown]");
            logger.info("Stopping reporting metric...");
            metricReporter.stop();
            logger.info("Stopping reporting metric...DONE");
        }

        @Override
        public void preIterationSetup() throws Exception {
            logger.trace("[preIterationSetup]");
        }

        @Override
        public void doIterationSetup() throws Exception {
            this.preIterationSetup();
            logger.trace("[doIterationSetup]");
            this.postIterationSetup();
        }

        @Override
        public void postIterationSetup()  throws Exception {
            logger.trace("[postIterationSetup]");
        }

        @Override
        public void preIterationTearDown() throws Exception{
            logger.trace("[preIterationTearDown]");
        }

        @Override
        public void doIterationTearDown() throws Exception{
            this.preIterationTearDown();
            logger.trace("[doIterationTearDown]");
            this.postIterationTearDown();
        }

        @Override
        public void postIterationTearDown()  throws Exception{
            logger.trace("[postIterationTearDown]");
        }

    }


    protected static abstract class AbstractSequentialExecutionPlan extends AbstractExecutionPlan {

        protected String finPath;

        protected String foutPath;

        protected File tempDir;

        @Override
        public void preTrialSetUp() throws Exception {
            super.preTrialSetUp();

            tempDir = Files.createTempDir();
            new File(tempDir.getAbsolutePath()).mkdirs();

            finPath = tempDir.getAbsolutePath() + "/in.data";
            foutPath = tempDir.getAbsolutePath() + "/out.data";

            FileUtils.touch(new File(finPath));
            FileUtils.touch(new File(foutPath));

            //Sequential data generation
            try(
                    FileOutputStream fin = new FileOutputStream(finPath);
            ) {
                for(int i = 0; i < TOTAL_DATA_WRITEN; i++) {
                    fin.write((byte) i);
                }
            }

            logger.debug("Temp dir created at [{}]", tempDir.getAbsolutePath());
            logger.debug("File created at [{}]", finPath);
            logger.debug("File created at [{}]", foutPath);
        }

        @Override
        public void postTrialTearDown() throws Exception {
            super.postTrialTearDown();

            FileUtils.deleteDirectory(tempDir);
            logger.debug("Temp dir deleted at [{}]", tempDir.getAbsolutePath());
        }
    }

    protected static class AbstractRandomAccessExecutionPlan extends AbstractExecutionPlan {

        protected String fmetaPath;

        protected String fsummaryPath;

        protected String finPath;

        protected List<String> listOfFoutPath = new ArrayList<>();

        protected File tempDir;

        protected enum DataType {
            INTEGER(1, 4),
            DOUBLE(2, 8),
            CHAR(3, 2),
            BYTE(4, 1);

            private int type;

            private int sizeOf;

            DataType(int id, int size) {
                type = id;
                this.sizeOf = size;
            }


            public static DataType getDataTypeByTypeId(int type) {
                switch(type) {
                    case 1:
                        return INTEGER;
                    case 2:
                        return DOUBLE;
                    case 3:
                        return CHAR;
                    case 4:
                        return BYTE;
                    default:
                        return null;
                }
            }

            public static DataType getDataTypeBySizeOf(int size) {
                switch(size) {
                    case 1:
                        return BYTE;
                    case 2:
                        return CHAR;
                    case 4:
                        return INTEGER;
                    case 8:
                        return DOUBLE;
                    default:
                        return null;
                }
            }

            public static int COUNT = DataType.values().length;
        }

        @Override
        public void preTrialSetUp() throws Exception {
            super.preTrialSetUp();

            Random rand = new Random(System.currentTimeMillis());

            tempDir = Files.createTempDir();
            new File(tempDir.getAbsolutePath()).mkdirs();

            finPath = tempDir.getAbsolutePath() + "/in.data";
            fmetaPath = tempDir.getAbsolutePath() + "/meta.data";
            fsummaryPath = tempDir.getAbsolutePath() + "/summary.data";

            FileUtils.touch(new File(finPath));
            FileUtils.touch(new File(fmetaPath));

            try (
                    PrintWriter fmeta = new PrintWriter(new FileOutputStream(fmetaPath));
                    PrintWriter fsummary = new PrintWriter(new FileOutputStream(fsummaryPath));
                    FileChannel channel = new RandomAccessFile(finPath, "rw").getChannel();
            ) {
                int foutFileSize[] = new int[COUNT];

                ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, TOTAL_DATA_WRITEN);

                for(int currentIndex = 0; currentIndex < TOTAL_DATA_WRITEN; ) {
                    DataType nextDataType = getDataTypeByTypeId(rand.nextInt(3) + 1);

                    if(nextDataType.sizeOf + currentIndex >= TOTAL_DATA_WRITEN) {
                        int remainingByte = TOTAL_DATA_WRITEN - currentIndex;
                        logger.debug("Remain bytes : [{}]", remainingByte);
                        nextDataType = DataType.getDataTypeBySizeOf(remainingByte);
                    }

                    fmeta.println(finPath + "," + nextDataType.type + "," + currentIndex + "," + nextDataType.sizeOf);

                    switch(nextDataType) {
                        case INTEGER:
                            buffer = buffer.putInt(currentIndex, rand.nextInt());
                            foutFileSize[nextDataType.ordinal()]  += nextDataType.sizeOf;
                            break;
                        case DOUBLE:
                            buffer = buffer.putDouble(currentIndex, rand.nextDouble());
                            foutFileSize[nextDataType.ordinal()]  += nextDataType.sizeOf;
                            break;
                        case CHAR:
                            buffer = buffer.putChar(currentIndex, (char)(rand.nextInt(26) + 'a'));
                            foutFileSize[nextDataType.ordinal()]  += nextDataType.sizeOf;
                            break;
                        case BYTE:
                            buffer = buffer.put(currentIndex, (byte) rand.nextInt());
                            foutFileSize[nextDataType.ordinal()]  += nextDataType.sizeOf;
                            break;
                        default:
                            break;
                    }

                    currentIndex += nextDataType.sizeOf;
                }

                buffer.flip();

                // Write summary file
                for (int i = 0; i < foutFileSize.length; i++) {
                    int size = foutFileSize[i];
                    fsummary.println(size);
                }
            }

            for(int i = 0; i < DataType.COUNT; i++) {
                String foutPath = tempDir.getAbsolutePath() + "/out-" + DataType.getDataTypeByTypeId(i+1).name() + ".data";
                FileUtils.touch(new File(foutPath));
                logger.debug("File created at [{}]", foutPath);
                listOfFoutPath.add(foutPath);
            }

            logger.debug("Temp dir created at [{}]", tempDir.getAbsolutePath());
            logger.debug("File created at [{}]", finPath);
            logger.debug("File created at [{}]", fmetaPath);
        }

        @Override
        public void postTrialTearDown() throws Exception {
            super.postTrialTearDown();

            FileUtils.deleteDirectory(tempDir);
            logger.debug("Temp dir deleted at [{}]", tempDir.getAbsolutePath());
        }
    }

//    public static void main(String[] args) throws Exception {
//        AbstractRandomAccessExecutionPlan plan = new AbstractRandomAccessExecutionPlan();
//        plan.preTrialSetUp();
//    }

}
