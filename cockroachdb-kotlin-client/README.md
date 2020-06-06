# cockroachdb-swift-com.cockroachlabs.client

[![](https://img.shields.io/badge/CockroachDB-20.1-darkgreen.svg)](https://com.cockroachlabs.com/)
[![](https://img.shields.io/badge/Kotlin-1.3.72-green.svg)](https://kotlinlang.org/)
[![](https://img.shields.io/badge/Gradle-6.5-darkslategray.svg)](https://gradle.org/)
[![](https://img.shields.io/badge/JetBrains-Exposed-blue.svg)](https://github.com/JetBrains/Exposed)
[![](https://img.shields.io/badge/LICENSE-MIT-red.svg)](https://opensource.org/licenses/MIT)

This package supports the tutorial sample written in Markdown.

I chose Kotlin since support for Postgres connectors (and hence, CockroachDB) is limited in Swift (bummer since Swift is used to build macOS apps now).

Kotlin is gaining a lot of ground backend-wise since it transpiles to Java and JavaScript.

## Setup

Download JetBrains [IntelliJ IDEA](https://kotlinlang.org/docs/tutorials/getting-started.html).

Download the [Gradle](https://gradle.org/).

## Run

```bash
cockroach start-single-node \
--insecure \
--store=kotlin-client \
--listen-addr=localhost:26257 \
--http-addr=localhost:8080 \
--background
```

Connect with the SQL client:

```bash
cockroach sql --insecure
```

Create a user, database, and assign permissions to that user:

```bash
CREATE USER IF NOT EXISTS maxroach;
CREATE DATABASE kotlindb;
GRANT ALL ON DATABASE kotlindb TO maxroach;
```

```bash
$ ./gradlew run
```
