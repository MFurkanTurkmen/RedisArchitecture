# Redis Architecture Project
## İçindekiler
1. [Proje Hakkında](#proje-hakkında)
2. [Teknolojiler ve Versiyonlar](#teknolojiler-ve-versiyonlar)
3. [Proje Yapısı](#proje-yapısı)
4. [Redis Entegrasyonu](#redis-entegrasyonu)
5. [Kurulum ve Çalıştırma](#kurulum-ve-çalıştırma)
6. [Konfigürasyon Detayları](#konfigürasyon-detayları)
7. [API Endpoints](#api-endpoints)
8. [Redis Cache Stratejileri](#redis-cache-stratejileri)
9. [Performans ve Monitoring](#performans-ve-monitoring)
10. [Troubleshooting](#troubleshooting)
## Proje Hakkında Bu proje, Spring Boot uygulamalarında Redis önbellekleme sisteminin nasıl kullanılacağını gösteren kapsamlı bir örnektir. Proje, kitap verilerinin yönetimi üzerinden Redis önbellekleme stratejilerini
demonstre eder.
### Temel Özellikler
- Kitap CRUD işlemleri
- Redis önbellekleme
- Otomatik önbellek yenileme
- Performans optimizasyonu
- Monitoring ve health check
## Teknolojiler ve Versiyonlar
- **Java 21**: Modern Java özellikleri için
- **Spring Boot 3.3.5**: Mikroservis mimarisi için
- **Redis 7.x**: Önbellekleme sistemi
- **PostgreSQL**: Ana veritabanı
- **Gradle**: Bağımlılık yönetimi
- **Spring Data JPA**: ORM katmanı
- **Spring Data Redis**: Redis entegrasyonu
- **Lombok**: Boilerplate kod azaltımı
- **MapStruct**: DTO dönüşümleri
- **SpringDoc OpenAPI**: API dokümantasyonu
## Proje Yapısı
```plaintext
src/
├── main/
│ ├── java/
│ │ └── com/mft/redisarchitecture/
│ │ ├── config/
│ │ ├── controller/
│ │ ├── dto/
│ │ ├── entity/
│ │ ├── exception/
│ │ ├── mapper/
│ │ ├── repository/
│ │ └── service/
│ └── resources/
│ └── application.yml
```
## Redis Entegrasyonu
### Redis Annotations
```java @EnableCaching // Önbellekleme sistemini aktifleştirir @Cacheable("books") // Metod sonucunu önbellekte saklar @CacheEvict // Önbellekteki veriyi siler @CachePut // Önbelleği günceller
```
### Redis Config Sınıfı
```java @Configuration @EnableCaching
public class RedisConfig {
    @Bean
    public RedisTemplate redisTemplate() {
// Redis bağlantı ve serialization ayarları
    }
}
```
## Kurulum ve Çalıştırma
1. Redis Kurulumu:
```bash
docker run --name redis -p 6379:6379 -d redis
```
2. PostgreSQL Kurulumu:
```bash
docker run --name postgres -e POSTGRES_PASSWORD=postgres -p 5444:5432 -d postgres
```
3. Uygulamayı Çalıştırma:
```bash
./gradlew bootRun
```
## Konfigürasyon Detayları
### application.yml
```yaml
spring:
datasource:
url: jdbc:postgresql://localhost:5444/redis
username: postgres
password: postgres
jpa:
hibernate:
ddl-auto: update
redis:
host: localhost
port: 6379
timeout: 10000ms
lettuce:
pool: max-active: 8 max-idle: 8 min-idle: 0 max-wait: -1ms
```
### Redis Bağlantı Havuzu
- **max-active**: Maksimum aktif bağlantı sayısı
- **max-idle**: Havuzda tutulacak maksimum boşta bağlantı
- **min-idle**: Minimum boşta bağlantı sayısı
- **max-wait**: Bağlantı bekleme süresi
## API Endpoints
### Book Controller
- `POST /book/create`: Yeni kitap oluşturma
- `GET /book/get?id={id}`: ID ile kitap getirme
- `GET /book/list?adet={adet}`: Belirtilen sayıda kitap listeleme
## Redis Cache Stratejileri
### Önbellekleme Yaklaşımları
1. **Look Aside Cache**:
```
java @Cacheable(value = "books", key = "#id")
public BookRS getBook(Long id) {
// Önce cache'den kontrol
// Cache'de yoksa DB'den getir
}
```
2. **Write Through Cache**:
```
java @CachePut(value = "books", key = "#result.id")
public BookRS createBook(BookRQ bookRQ) {
// Hem DB'ye hem cache'e yaz
}
```
### TTL (Time To Live) Stratejisi
```java
redisCacheService.saveWithExpiration(key, value, CACHE_TTL, TimeUnit.HOURS);
```
## Performans ve Monitoring
### Actuator Endpoints
```yaml management:
endpoints: web:
exposure:
include: health,info
```
### Health Indicators
- Redis bağlantı durumu
- Database bağlantı durumu
- Uygulama sağlık durumu
## Troubleshooting
### Sık Karşılaşılan Hatalar
1. Redis Bağlantı Hataları
``` 
Çözüm: Redis sunucusunun çalıştığını kontrol edin
```
2. Serialization Hataları
``` 
Çözüm: Entity sınıflarının Serializable interface'ini implement ettiğinden emin olun
```
### Performans İyileştirmeleri
1. Connection Pool Optimizasyonu
2. Uygun TTL Değerleri
3. Önbellek Boyutu Yönetimi