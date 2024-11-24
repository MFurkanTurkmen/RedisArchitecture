# Redis Cache Service ve Spring Redis Anotasyonları

## Redis Cache Service
Redis Cache Service, uygulamamızda verileri önbellekleme işlemlerini yönetmek için kullanılan bir servistir. Bu servis sayesinde:
- Sık kullanılan verileri RAM'de saklayabilir
- Veritabanı yükünü azaltabilir
- Uygulama performansını artırabiliriz

## Spring Redis Anotasyonları

### @EnableCaching
Spring uygulamasında önbellekleme özelliğini aktif hale getirir. Genellikle configuration sınıfında kullanılır.

### @Cacheable
Metod sonuçlarını önbellekte saklar. İlk çağrıda metod çalışır ve sonuç önbelleğe alınır, sonraki çağrılarda önbellekteki veri döndürülür.
```java
@Cacheable(value = "users", key = "#id")
public User getUser(String id) {
    // metod içeriği
}
```

### @CachePut
Metod sonucunu her zaman çalıştırır ve sonucu önbelleğe kaydeder. Veriyi güncellerken kullanılır.
```java
@CachePut(value = "users", key = "#user.id")
public User updateUser(User user) {
    // metod içeriği
}
```

### @CacheEvict
Önbellekteki veriyi siler. Veri silindiğinde veya güncellendiğinde kullanılır.
```java
@CacheEvict(value = "users", key = "#id")
public void deleteUser(String id) {
    // metod içeriği
}
```

### @Caching
Birden fazla önbellek işlemini birleştirmek için kullanılır.
```java
@Caching(evict = {
    @CacheEvict("users"),
    @CacheEvict("roles")
})
public void clearCache() {
    // metod içeriği
}
```

## Önbellek Süreleri (TTL)
Redis'te saklanan verilere yaşam süresi (Time To Live - TTL) atanabilir:
```java
@Cacheable(value = "users", key = "#id", timeout = 3600)
public User getUser(String id) {
    // metod içeriği
}
```

## Redis Cache Service Kullanım Örneği
```java
@Service
public class UserService {
    @Autowired
    private RedisCacheService redisCacheService;

    public User getUser(String id) {
        String key = "user:" + id;
        return redisCacheService.get(key, User.class)
            .orElseGet(() -> {
                User user = getUserFromDatabase(id);
                redisCacheService.put(key, user);
                return user;
            });
    }
}
```





