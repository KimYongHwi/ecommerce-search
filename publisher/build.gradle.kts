import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = true
jar.enabled = false

dependencies {
    implementation(project(":domain"))
    implementation(project(":infrastructure:queue:kafka"))

    implementation("com.github.doyaaaaaken:kotlin-csv:1.9.3")
}
