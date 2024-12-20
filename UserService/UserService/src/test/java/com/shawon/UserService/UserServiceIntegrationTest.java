package com.shawon.UserService;

import com.shawon.UserService.model.User;
import com.shawon.UserService.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@SpringBootTest
public class UserServiceIntegrationTest {

    @Container
    static MongoDBContainer mongoContainer = new MongoDBContainer("mongo:6.0.5");

    @Container
    static GenericContainer<?> redisContainer = new GenericContainer<>("redis:6.2").withExposedPorts(6379);

    @Container
    static KafkaContainer kafkaContainer = new KafkaContainer();

    @Autowired
    private UserRepository userRepository;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @BeforeAll
    static void setup() {
        mongoContainer.start();
        redisContainer.start();
        kafkaContainer.start();
    }

    @Test
    public void testUserCreationAndRetrieval() {
        // Arrange
        User user = new User(null, "john.doe@example.com", "John Doe", "password123");
        userRepository.save(user);

        // Act
        Optional<User> retrievedUser = Optional.ofNullable(userRepository.findByEmail("john.doe@example.com"));

        // Assert
        assertNotNull(retrievedUser);
        assertEquals("John Doe", retrievedUser.get().getName());
    }

    @Test
    public void testKafkaMessageSent() {
        // Simulate sending a message to Kafka
        kafkaTemplate.send("user-events", "user-created", "Test message");
        // In real tests, you can use Kafka consumers to validate the message was received
    }

    @Test
    public void testRedisConnection() {
        // Example: Test Redis is accessible and operations work
        redisTemplate.opsForValue().set("test-key", "test-value");
        String value = (String) redisTemplate.opsForValue().get("test-key");

        assertEquals("test-value", value);
    }
}
