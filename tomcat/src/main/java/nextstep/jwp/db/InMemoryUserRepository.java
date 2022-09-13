package nextstep.jwp.db;

import nextstep.jwp.model.User;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository {

    private static final Map<String, User> database = new ConcurrentHashMap<>();

    static {
        initData();
    }

    private InMemoryUserRepository() {}

    public static void save(User user) {
        database.put(user.getAccount(), user);
    }

    public static Optional<User> findByAccount(String account) {
        return Optional.ofNullable(database.get(account));
    }

    public static Optional<User> findByEmail(String email) {
        return database.values()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findAny();
    }

    public static void initData() {
        final User user = new User(1L, "philz", "1234", "philz@hello.com");
        database.put(user.getAccount(), user);
    }
}
