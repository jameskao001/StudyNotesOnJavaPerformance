package com.github.mcfongtw;

import org.apache.commons.lang3.RandomStringUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class StringBuilderPerfDeathMatch {

    @State(Scope.Benchmark)
    public static class MatchState {
        public static final int ROUND = 100000;
        public static final String FIXED = "FIXED";
    }


    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
//    @Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    public void measureStringBufferFixed(MatchState state) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < state.ROUND; i++) {
            stringBuffer.append(state.FIXED);
        }
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
//    @Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    public void measureStringBufferRandom(MatchState state) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < state.ROUND; i++) {
            stringBuffer.append(RandomStringUtils.randomAlphabetic(100));
        }
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
//    @Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    public void measureStringBuilderRandom(MatchState state) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < state.ROUND; i++) {
            stringBuilder.append(RandomStringUtils.randomAlphabetic(100));
        }
    }

    @Benchmark
    @BenchmarkMode({Mode.AverageTime, Mode.Throughput})
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
//    @Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
    public void measureStringBuilderFixed(MatchState state) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < state.ROUND; i++) {
            stringBuilder.append(state.FIXED);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StringBuilderPerfDeathMatch.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
