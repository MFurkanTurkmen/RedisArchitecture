# Redis Cache Service ve Spring Redis Uygulaması

## İçindekiler
- [Proje Hakkında](#proje-hakkında)
- [Mimari](#mimari)
- [Redis Annotations](#redis-annotations)
- [Konfigürasyon](#konfigürasyon)
- [Monitoring](#monitoring)

## Proje Hakkında
Bu proje, Spring Boot uygulamalarında Redis önbellekleme sisteminin nasıl kullanılacağını gösteren bir örnek uygulamadır. Redis Cache Service ile veritabanı yükünü azaltıp, uygulama performansını artırabiliriz.

## Mimari
![Redis Architecture](docs/images/redis-architecture.png)
![System Architecture](docs/images/system-architecture.png)

## Redis Annotations
![Redis Annotations](docs/images/redis-annotations.png)

### Temel Annotations
- @EnableCaching: Spring uygulamasında önbellekleme özelliğini aktif hale getirir
- @Cacheable: Metod sonuçlarını önbellekte saklar
- @CachePut: Metod sonucunu her zaman çalıştırır ve önbelleğe kaydeder
- @CacheEvict: Önbellekteki veriyi siler

## Konfigürasyon
```java
@Configuration
@EnableCaching
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        // Redis konfigürasyon detayları
    }
}
```

## Monitoring
![Redis Monitoring](docs/images/redis-monitoring.png)

Redis performans metrikleri ve sistem durumu izleme ekranı üzerinden takip edilebilir. Önemli metrikler:
- Memory kullanımı
- Bağlantı sayısı
- Cache hit/miss oranları
- Komut istatistikleri





