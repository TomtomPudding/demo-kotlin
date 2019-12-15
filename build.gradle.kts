
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jooq.codegen.GenerationTool
import org.jooq.meta.jaxb.Database
import org.jooq.meta.jaxb.Generator
import org.jooq.meta.jaxb.Jdbc
import org.jooq.meta.jaxb.Target

buildscript {
	dependencies {
		classpath("mysql:mysql-connector-java:8.0.18")
	}
}

plugins {
	id("org.springframework.boot") version "2.2.0.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	id("nu.studer.jooq") version "3.0.3"
	id ("org.flywaydb.flyway") version "6.1.1"
	kotlin("jvm") version "1.3.50"
	kotlin("plugin.spring") version "1.3.50"
}
group = "com.chartapp"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

val developmentOnly by configurations.creating
configurations {
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}


dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-redis:1.4.7.RELEASE")
	implementation("org.springframework.session:spring-session-data-redis")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.0")
	runtime("mysql:mysql-connector-java:8.0.18")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.50")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.50")
	implementation ("io.springfox:springfox-swagger2:2.9.2")
	implementation ("io.springfox:springfox-swagger-ui:2.9.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test:2.2.0.RELEASE") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}

	runtime ("mysql:mysql-connector-java:8.0.18")
	//compile  ("org.jooq:jooq:3.0.3")
	//compile  ("org.jooq:jooq-codegen:3.0.3")
	//compile  ("org.jooq:jooq-meta:3.0.3")
	compile ("org.springframework.boot:spring-boot-starter-jooq")
	testCompile ("org.flywaydb:flyway-core")
	testCompile ("org.springframework.boot:spring-boot-starter-data-jdbc:2.2.0.RELEASE")
}

flyway {
	url = "jdbc:mysql://localhost:3306/chart-api-server"
	user = "owner"
	password = "initpass"
}

jooq {
	sourceSets{
		main {
			withGroovyBuilder{
				val configuration = org.jooq.meta.jaxb.Configuration().apply {
					jdbc = Jdbc().apply {
						driver = "com.mysql.cj.jdbc.Driver"
						url = "jdbc:mysql://localhost:3306/chart-api-server"
						user = "owner"
						password = "initpass"
						schema = "chart-api-server"
					}
					generator = Generator().apply {
						database = Database().apply {
							name = "org.jooq.meta.mysql.MySQLDatabase"
							includes = ".*"
							excludes = "schema_version"
							inputSchema = "chart-api-server"
						}
						target = Target().apply {
							packageName = "com.chartapp.jooq.codes.foobar.cv"
							directory = "src/main/java"
						}
					}
				}
				GenerationTool.generate(configuration)
			}
		}
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}



tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

