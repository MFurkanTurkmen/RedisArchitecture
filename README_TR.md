# Redis Cache Service ve Spring Redis Uygulaması

## Proje Hakkında
Bu proje, Spring Boot ve Redis kullanarak yüksek performanslı bir önbellekleme sistemi oluşturmayı amaçlamaktadır. Uygulama, kitap verilerini yönetmek ve önbelleklemek için geliştirilmiş bir REST API sunmaktadır.

## Özellikler
- Kitap verilerinin CRUD işlemleri
- Redis önbellekleme desteği
- Özelleştirilebilir TTL süreleri
- Otomatik önbellek yenileme
- Yüksek performanslı veri erişimi

## Teknolojiler
- Spring Boot 3.3.5
- Redis
- PostgreSQL
- Lombok
- MapStruct
- Spring Data JPA
- Spring Cache
- Lettuce Redis Client

## Gereksinimler
- Java 21
- Redis Server
- PostgreSQL
- Gradle

## Sistem Mimarisi
Uygulama üç katmanlı bir mimariye sahiptir:
- Controller Katmanı: HTTP isteklerini karşılar
- Service Katmanı: İş mantığını yönetir
- Repository Katmanı: Veri erişimini sağlar

## Kurulum ve Çalıştırma
1. Redis Server'ı başlatın
2. PostgreSQL veritabanını oluşturun
3. application.yml dosyasını konfigüre edin
4. Gradle ile projeyi derleyin: `./gradlew build`
5. Uygulamayı başlatın: `./gradlew bootRun`

## API Endpoints
- POST /book/create: Yeni kitap oluşturur
- GET /book/get?id={id}: ID'ye göre kitap getirir
- GET /book/list?adet={adet}: Belirtilen sayıda kitap listeler

## Konfigürasyon
Redis ve veritabanı ayarları application.yml dosyasında yapılandırılabilir:
- Redis host, port ve timeout
- PostgreSQL bağlantı bilgileri
- Cache TTL süreleri

## Monitoring
- /actuator/health: Sistem sağlık durumu
- /actuator/info: Uygulama bilgileri
- Zipkin entegrasyonu ile distributed tracing

## Test
Testleri çalıştırmak için:
```bash
./gradlew test
```

## Build
Projeyi derlemek için:
```bash
./gradlew clean build
```

## Cache TTL Ayarları
- Varsayılan TTL: 1 saat
- Özel TTL tanımlamaları service katmanında yapılabilir
- Her cache için ayrı TTL tanımlanabilir

## Hata Yönetimi
- GlobalExceptionHandler ile merkezi hata yönetimi
- Özelleştirilmiş hata kodları ve mesajları
- Redis bağlantı hataları için fallback mekanizması

## Katkıda Bulunma
1. Fork yapın
2. Feature branch oluşturun
3. Değişikliklerinizi commit edin
4. Branch'inizi push edin
5. Pull request açın

## Lisans
Bu proje MIT lisansı altında lisanslanmıştır.

## İletişim
- GitHub: [MFurkanTurkmen](https://github.com/MFurkanTurkmen)
- Email: [email protected]

## Teşekkürler
- Spring Framework ekibine
- Redis topluluğuna
- Tüm katkıda bulunanlara





