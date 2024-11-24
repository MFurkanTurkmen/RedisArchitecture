# Redis Mimarisi

## Proje Genel Bakış
Redis önbellek yapısını kullanan Spring Boot tabanlı bir uygulama.

## Özellikler
- Redis önbellekleme
- RESTful API endpoints
- Spring Boot entegrasyonu

## Teknolojiler
- Spring Boot
- Redis
- Gradle
- Java

## Sistem Mimarisi
- Redis Cache Layer
- Spring Service Layer
- REST Controller Layer

## Redis Önbellekleme Anotasyonları
```java
@Cacheable
@CachePut
@CacheEvict
```

## Konfigürasyon
```yaml
spring:
  redis:
    host: localhost
    port: 6379
```

## Kurulum
1. Redis Server kurulumu
2. Projeyi klonlama
3. Gradle bağımlılıklarını yükleme
4. Uygulamayı başlatma

## API Dokümantasyonu
- GET /api/data
- POST /api/data
- DELETE /api/data

## İzleme Endpointleri
- /actuator/health
- /actuator/metrics

## Katkıda Bulunma
1. Fork yapın
2. Feature branch oluşturun
3. Değişikliklerinizi commit edin
4. Pull request açın

## Lisans
MIT License





