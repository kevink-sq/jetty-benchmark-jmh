# Description

[jmh](https://github.com/openjdk/jmh) benchmarking against [jetty-poc](https://github.com/kevink-sq/jetty-concurrency-issue-poc)

## To use

start jetty-concurrency-issue-poc server via:

```
mvn compile exec:java -Dexec.mainClass="org.example.Main"
```

in this repo

```
mvn clean verify
```

different benchmarking setups

```
# To test against the HTTP endpoint
java -jar -Dcommand="curl --unix-socket http://localhost:8090/process" target/benchmarks.jar

# To test against the NATIVE endpoint
java -jar -Dcommand="curl --unix-socket <jetty-concurrency-issue-poc>/native-ingress.sock http://localhost/process" target/benchmarks.jar

# TO test aginst the JUNIX endpoint
java -jar -Dcommand="curl --unix-socket <jetty-concurrency-issue-poc>/jnr-ingress.sock http://localhost/process" target/benchmarks.jar

```

## Benchmark (Oct 31 2023)
### First Round
- Debug log was enabled on the poc server

```
NATIVE Test
-----------
Result "org.sample.MyBenchmark.testMethod":
  62.327 ±(99.9%) 1.403 ops/s [Average]
  (min, avg, max) = (58.862, 62.327, 66.004), stdev = 1.873
  CI (99.9%): [60.924, 63.729] (assumes normal distribution)

Benchmark                Mode  Cnt   Score   Error  Units
MyBenchmark.testMethod  thrpt   25  62.327 ± 1.403  ops/s

JUNIX Test
-----------
Result "org.sample.MyBenchmark.testMethod":
  65.812 ±(99.9%) 1.315 ops/s [Average]
  (min, avg, max) = (61.628, 65.812, 68.316), stdev = 1.756
  CI (99.9%): [64.496, 67.127] (assumes normal distribution)

Benchmark                Mode  Cnt   Score   Error  Units
MyBenchmark.testMethod  thrpt   25  65.812 ± 1.315  ops/s

HTTP Test
-----------
Result "org.sample.MyBenchmark.testMethod":
  51.642 ±(99.9%) 3.930 ops/s [Average]
  (min, avg, max) = (44.582, 51.642, 59.852), stdev = 5.247
  CI (99.9%): [47.711, 55.572] (assumes normal distribution)

Benchmark                Mode  Cnt   Score   Error  Units
MyBenchmark.testMethod  thrpt   25  51.642 ± 3.930  ops/s
```

### Second Round
- Running against jetty poc https://github.com/kevink-sq/jetty-concurrency-issue-poc/commit/b2feec4eee349a96c2db222029fe0be8b2bb0b97
- Debug log disabled this time

results

```
NATIVE Test
-----------
Result "org.sample.MyBenchmark.testMethod":
  58.182 ±(99.9%) 2.866 ops/s [Average]
  (min, avg, max) = (55.142, 58.182, 69.284), stdev = 3.826
  CI (99.9%): [55.316, 61.048] (assumes normal distribution)
  
  Benchmark                Mode  Cnt   Score   Error  Units
MyBenchmark.testMethod  thrpt   25  58.182 ± 2.866  ops/s

JUNIX Test
-----------
Result "org.sample.MyBenchmark.testMethod":
  63.512 ±(99.9%) 4.778 ops/s [Average]
  (min, avg, max) = (55.549, 63.512, 74.714), stdev = 6.378
  CI (99.9%): [58.734, 68.290] (assumes normal distribution)

Benchmark                Mode  Cnt   Score   Error  Units
MyBenchmark.testMethod  thrpt   25  63.512 ± 4.778  ops/s

HTTP Test
-----------
Result "org.sample.MyBenchmark.testMethod":
  52.973 ±(99.9%) 2.469 ops/s [Average]
  (min, avg, max) = (49.480, 52.973, 63.815), stdev = 3.296
  CI (99.9%): [50.504, 55.442] (assumes normal distribution)

Benchmark                Mode  Cnt   Score   Error  Units
MyBenchmark.testMethod  thrpt   25  52.973 ± 2.469  ops/s
```

