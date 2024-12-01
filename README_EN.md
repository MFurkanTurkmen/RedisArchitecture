# Redis Architecture Project
## İçindekiler
1. [Proje Hakkında](#proje-hakkında)
2. [Teknolojiler ve Versiyonlar](#teknolojiler-ve-versiyonlar)
3. [Proje Yapısı](#için)
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

## Redis Entegrasyonu
### Redis Annotations
```java
@EnableCaching // Önbellekleme sistemini aktifleştirir 
@Cacheable("books") // Metod sonucunu önbellekte saklar 
@CacheEvict // Önbellekteki veriyi siler 
@CachePut // Önbelleği günceller
```
### Redis Config Sınıfı
``` 
 @Configuration @EnableCaching
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
docker run --name postgres -e POSTGRES_PASSWORD="şifreniz" -p 5444:5432 -d postgres
```
3. Uygulamayı Çalıştırma:
```bash
./gradlew bootRun
```
## Konfigürasyon Detayları
### application.yml
```yaml
server:
  port: 80
spring:
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5444/redis
    username: kullanici-adiniz
    password: şifreniz
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
redis:
  host: localhost
  port: 6379
  timeout: 10000ms
  lettuce:
    pool:
      max-active: 8      
      max-idle: 8        
      min-idle: 0     
      max-wait: -1ms    


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
- **Look Aside Cache**: Önce önbellekte arama yapar, yoksa veritabanından getirir
- **Write Through Cache**: Hem önbelleğe hem veritabanına yazma işlemi
- **Write Behind Cache**: Önce veritabanına yazma, ardından önbelleğe yazma
- **Refresh Ahead Cache**: Önbelleği belirli aralıklarla yenileme
- **Cache Aside Cache**: Önbelleği sadece okuma işlemlerinde kullanma
- **Read Through Cache**: Önbellek yoksa veritabanından getirme
- **Read Around Cache**: Önbellek yoksa veritabanından getirme, önbelleğe yazma
- **Write Around Cache**: Önbellekten veri silme
- **Write Only Cache**: Sadece yazma işlemleri
- **Read Only Cache**: Sadece okuma işlemleri
- **Write Once Cache**: Sadece bir kere yazma işlemi
- **Write Behind Cache**: Asenkron yazma işlemi
- **Near Cache**: Uygulama sunucusuna yakın önbellek
- **Distributed Cache**: Dağıtık önbellek
- **Local Cache**: Yerel önbellek
- **Remote Cache**: Uzak önbellek
- **In-Memory Cache**: Bellek üzerinde
- **On-Disk Cache**: Disk üzerinde
- **Hybrid Cache**: Karma önbellek
- **Cache-Aside Cache**: Önbelleği sadece okuma işlemlerinde kullanma
### Örnek Kullanımlar
#### Önbelleğe Alma
```java
@Cacheable("books")
public Book getBookById(Long id) {
    return bookRepository.findById(id).orElse(null);
}
```
#### Önbellekten Silme
```java
@CacheEvict("books")
public void deleteBookById(Long id) {
    bookRepository.deleteById(id);
}
```

#### Önbelleği Güncelleme
```java
@CachePut("books")
public Book updateBook(Book book) {
    return bookRepository.save(book);
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