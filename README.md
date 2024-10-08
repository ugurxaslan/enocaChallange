# Enoca Java Challange

Projeyi **7 ağustos çarşamba** günü aldım. Okulun açılma tarihinin yaklaşması ve staj yapabilme süresinin daralması, staja bir an önce başlayabilme nedeniyle projeyi **9 ağustos cuma** günü eksik bir şekilde teslim ettim. Haftasonu boş kalmamak amacıyla eksiklerimi tamamladım ve **11 ağustos pazar** günü tamamen bitirdim. Proje üzerinde **4 gün** çalıştım.

### Çalıştırma

`src\main\resources\application.properties` dosyasını kendi yerel bilgisayarınıza göre güncelleyin. Çalıştırmak için `e-commerce` dizinini içerisine girip `mvn install` ve ardından `mvn spring-boot:run` yazın.

## POSTMAN
https://www.postman.com/science-administrator-19559804/workspace/enoca-api-test
## Customer Service Endpoints

![Customer Service Photo](readme.img/2.png)

#### `addCustomer` [/customers/addCustomer]

Yeni bir kullanıcıyı Sisteme dahil eder.

#### `getCustomer` [/customers/{customerId}/getCustomer]

Sistemdeki bir kullanıcıyı id'sine göre getirir.

## Product Service Endpoints

![Product Service Photo](readme.img/4.png)

#### `createProduct` [/products/createProduct]

Bilgileri verilen ürünü sisteme dahil eder.

#### `getProduct` [/products/{productId}getProduct]

Bilgileri verilen ürünü sistemden getirir.

Ürün bilgilerini güncellemek için kullanılır.

#### `deleteProduct` [/products/{productId}deleteProduct]

Bilgileri verilen ürünü sistemden siler fakat sepette veya siparişte bu ürün varsa silmekte hata alıyorum tasarımsal bir hata bu.

## Cart Service Endpoints

![Cart Service Photo](readme.img/1.png)

#### `getCart` [/carts/{customerId}/getCart]

Kullanıcı id si verilen kişinin sepetini getirir.

#### `updateCart` [/carts/{customerId}/updateCart]

Id'si verilen müşterinin sepetindeki ürünler stok miktarlarına göre kontrol edilir. Normalde stokta yeterli ürün yoksa bu ürün sepete eklenmez. Ancak başka bir müşteri tarafından sipariş verilmiş olabilir ve stok miktarları azalmış olabilir. Eğer stok miktarından fazla ürün varsa fazla ürünler sepetten düşürülür. Total price hesaplanır.

#### `addProductToCart` [/carts/{customerId}/addProductToCart]

Verilen ürünü müşterinin sepetine eğer ürün stokta varsa ekler. Total price hesaplanır.

#### `removeProductFromCart` [/carts/{customerId}/removeProductFromCart]

Verilen ürünü eğer sepette birden fazla varsa miktarını 1 azaltır. Eğer 1 tane varsa ürünü sepetten siler. Total price hesaplanır.

#### `emptyCart` [/carts/{customerId}/emptyCart]

Sepetteki tüm ürünleri kaldırır. Sepet boşalır ve total price hesaplanır.

## Order Service Endpoints

![Order Service Photo](readme.img/3.png)

#### `placeOrder` [/orders/{customerId}/placeOrder]

Id'si verilen müşterinin sepetindeki ürünleri önce stok miktarlarında göre kontrol eder stoklar yeterliyse sipariş verir. Her ürünün sipariş anındaki fiyatı order item içerisinde tutulur anlık fiyat ise product üzerinde yazar. Bu sayede kullanıcı aldığı tarihteki fiyatı geçmişe yönelik görebilir. Sipariş sonucunda order içerisine bir ordercode oluşturulur bu kod sayesinde kullanıcı siparişine ulaşabilir. Sipariş öncesi stoklar güncellenir.

#### `getOrderForCode` [/orders/{orderCode}/getOrder]

Verilen coda göre sipariş getirilir içerisinde ürünlr bulunu ve alış fiyatı gözükür.

#### `getAllOrder` [/orders/{customerId}/getAllOrder]

Id'si verilen kişinin tüm siparişlerini getirir.
