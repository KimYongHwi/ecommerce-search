import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    val openSearchRestClientVersion: String by project
    val openSearchJavaVersion: String by project

    api("org.opensearch.client:opensearch-rest-client:${openSearchRestClientVersion}")
    api("org.opensearch.client:opensearch-java:${openSearchJavaVersion}")
}
