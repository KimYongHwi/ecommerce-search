import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = true
jar.enabled = false

dependencies {
    implementation(project(":domain"))
    implementation(project(":infrastructure:queue:kafka"))
    implementation(project(":infrastructure:nosql:opensearch"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
}
