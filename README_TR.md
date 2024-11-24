# Redis Önbellekleme Mimarisi

## Proje Hakkında
Bu proje, Spring Boot uygulamalarında Redis önbellekleme sisteminin nasıl uygulanacağını göstermektedir.

## Teknolojiler
- Java 17
- Spring Boot
- Redis
- Gradle
- Docker

## Mimari Yapı
- Redis Cache yapılandırması
- Service katmanında önbellekleme
- Repository pattern
- RESTful API endpoints

## Kurulum
1. Redis'i Docker ile başlatın:
```docker run --name redis -p 6379:6379 -d redis```
2. Projeyi klonlayın
3. Gradle ile bağımlılıkları yükleyin
4. Uygulamayı başlatın

## Kullanım
API endpointleri ve önbellekleme örnekleri için controller sınıflarını inceleyin.

## Katkıda Bulunma
1. Fork edin
2. Feature branch oluşturun
3. Değişikliklerinizi commit edin
4. Branch'inizi push edin
5. Pull request açın





