<h2 align="center">
Notification Service
</h2>

<p align="center">Send messages through rabbit queues to integrations like discord and telegram</p>

## Requirements

- [JDK 17](https://sdkman.io/)
- [Kotlin 1.7.10](https://sdkman.io/)

## Technologies/Frameworks

- [Gradle](https://gradle.org/)
- [Ktor](https://ktor.io/)
- [RabbitMQ](https://www.rabbitmq.com/)
- [JUnit4](https://junit.org/junit4/)

## Start

To start the application, execute a Gradle Wrapper *run* task.

```bash
./gradlew run
```

## Endpoints

### REST

- ***POST*** /notifications

<details>
 <summary><b>Request</b></summary>

```json
{
  "content": "content message",
  "metadata": {
    "routingKey": "discord|telegram"
  }
}
```
</details>

<details>
 <summary><b>Response</b></summary>

```json
{
  "content": "content message",
  "metadata": {
    "routingKey": "discord|telegram"
  }
}
```
</details>
