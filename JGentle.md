## Giới thiệu về JGentle Framework ##

**I. Giới thiệu:**

Hệ thống JGentle Framework là một AOE (Aspect Oriented Environment),
một lightweight container kết hợp với một tập hợp các API giúp đơn
giản hóa các vấn đề về Dependency Injection (DI) , Inversion Of
Control (IoC), Aspect Oriented Programing(AOP), ... Nhưng không giống
như một số framework, hay các AOP framework khác tập trung vào việc xử
lý AOP hay cung cấp một IoC container đơn thuần dựa trên việc cấu hình
với XML, JGentle cung cấp các chức năng DI và AOP hướng đến tính mở
rộng và tương thích với các hệ AOE khác (như Spring, ... ), kết hợp
với annotation (được chuyển đổi thành Definition - một loại metadata
cất trữ thông tin annotation) để trợ giúp cho hệ thống cấu hình, và
đồng thời đoạn tuyệt hoàn toàn với việc sử dụng XML hay YAML làm dữ
liệu định nghĩa thông tin cấu hình.

Dựa trên nền DI, AOI (Annotation Oriented Injection), SH (Service
Handling), dDI (Deep Dependency Injection), Definition, ... JGentle
cung cấp các services quản lý như Event Services, Queued Component,
Data Locator, RMI Wrapper, Object Pooling... và cung cấp một cơ chế
cấu hình ứng dụng sử dụng annotation, Definition một cách tùy biến.
JGentle hoàn toàn là POJOs, xây dựng dựa trên các đối tượng pure java
(thuần java) nên có thể tích hợp, tương thích với bất kì hệ thống nào
phát triển bằng Java. Mặt khác JGentle AOP tuân thủ chặt chẽ đặc tả kĩ
thuật của AOPAlliance nên các thành phần AOP trong JGentle hoàn toàn
có thể tương thích với các thành phần AOP được xây dựng trên bất kì
một AOE nào, miễn là AOE đó tuân thủ theo AOPAlliance.

Kết hợp điểm mạnh của các hệ thống khác, đơn giản hóa các API thao
tác, JGentle đã tạo ra một environment mạnh mẽ để phát triển các
modules tách rời với các thông tin cấu hình hệ thống, giúp cho hệ
thống ổn định, chịu đựng được các phát sinh mở rộng và linh hoạt khi
phát triển cũng như bảo trì.

**II. Tại sao sử dụng JGentle:**

Một vài lý do cho mục đích sử dụng JGentle thay vì các framework khác:

• Mục tiêu của JGentle là nhắm đến một khía cạnh quan trọng mà các
framework hiện tại không thực hiện hoặc chưa thực hiện. Đó là cung cấp
một cơ chế quản lý các business objects, một cơ chế DI thông qua
annotation, Definition, loại bỏ hoàn toàn sự phụ thuộc với cách truyền
thống trong việc sử dụng các XML files hoặc property files làm dữ liệu
cấu hình, đồng thời thay thế cách sử dụng metadata với annotation trực
tiếp bằng thông tin dữ liệu Definition gián tiếp, tách rời logic code
với thông tin annotation.

• JGentle gia tăng khả năng kết nối và tích hợp với các framework khác
(như Hibernate, Lucene, ...) cũng như các kĩ thuật chuẩn khác trên nền
JAVA như JDBC, RMI, JMX, ... cung cấp nhiều xử lý tự động khác nhau
với các mã lệnh phải quản lý ở mức low-level. JGentle được thiết kế
như là một giải pháp tổng thể dựa trên nhiều kĩ thuật khác nhau, tạo
lập một nền tảng trung tâm kết nối với các nền tảng kĩ thuật tách rời
khác, giảm thiểu một số lượng lớn công việc cho nhà phát triển nếu như
muốn tích hợp nhiều kĩ thuật khác nhau trên cùng một ứng dụng.

• Thành phần cốt lõi của JGentle là một Inversion Of Control container
(IoC) hay còn có thể được biết đến với một khái niệm tương tự đó là
Dependency Injection (DI) - một thuật ngữ được đưa ra bởi Martin
Fowler và Rod Johnson (cha đẻ Spring framework) cùng nhóm phát triển
PicoContainer vào khoảng cuối năm 2003. Nhưng thay vì như một số
framework hỗ trợ DI khác, chỉ cho phép chỉ định dependencies tại thời
điểm creation-time (vd Spring), JGentle IoC còn cho phép chỉ định các
dependencies tại thời điểm invocation time (thời điểm lúc bean được
triệu gọi thực thi). DI trong JGentle đưa ra nhiều cải tiến, cho phép
nhà phát triển filter được dữ liệu dependencies, chỉ định inject các
dependencies khác nhau trong những điều kiện khác nhau, đồng thời còn
cho phép outject (khái niệm ngược lại với inject) các dữ liệu
dependencies ngược trở lại lại container theo một scope chỉ định. Và
điều quan trọng là thông tin cấu hình DI trong JGentle hoàn toàn là
annotation, các cách thức wiring beans cũng như việc chỉ định
dependencies trong JGentle không hề phụ thuộc vào bất kì một file XML
cấu hình nào nhưng vẫn đảm bảo dữ liệu thông tin cấu hình độc lập với
logic code.

• JGentle cung cấp một cơ chế framework toàn diện cho Data Access, bao
gồm việc tương tác với JDBC hay một ORM framework bất kì như
Hibernate, …

• JGentle tự bên trong có cung cấp một hệ thống các services khác
nhau, cung cấp các chức năng khác nhau trợ giúp cho việc phát triển và
xây dựng hệ thống. Không những thế, JGentle còn cho phép các nhà phát
triển cấp thấp có thể tự xây dựng các hệ thống services khác dựa trên
nền JGentle, cũng như tận dụng được những điểm mạnh trong JGentle như
DI, AOP, AOI, SH... trong khi xây dựng services. Services sau khi được
xây dựng có thể được sử dụng như là một thành phần trong JGentle
container.

• JGentle cung cấp một cơ chế quản lý metadata dựa trên annotation,
thay thế cách thức sử dụng annotation một cách trực tiếp thông qua
reflection, thay vào đó sử dụng gián tiếp thông qua Definition (một
loại object mà thông tin dữ liệu được chuyển đổi từ annotation), cho
phép tách rời nội dung metadata và logic code khi cần thiết hoặc tùy
biến trong việc chỉnh sửa thông tin.

• Cung cấp một cơ chế Annotation Oriented Injection (AOI), cho phép
inject các annotations khác nhau vào logic code tại thời điểm run-time
mà không cần thao tác vào source code. Dữ liệu annotation giờ đây
trong JGentle có thể xem như là những dependency instances, có thể
inject vào bất kì vị trí nào chỉ định.

• JGentle hỗ trợ bên trong một hệ thống framework con, Aspect Oriented
Programming framework (AOP), được chỉ định cấu hình hoàn toàn với
annotation, tuân thủ AOPAlliance, do đó đảm bảo có thể tích hợp được
với AOP framework khác miễn là AOE (Aspect Oriented Environment) trên
các hệ framework này cũng tuân thủ AOPAlliance.

• Được thiết kế nhắm đến việc mở rộng và tương thích với các hệ
lightweight container và các framework hiện hành. Cho phép các beans
được quản lý bởi JGentle có thể tương tác với các beans được quản lý
trên các hệ container khác vd như Spring, Hivemind, ... Điều này giúp
tăng tính khả chuyển, cho phép các ứng dụng đã được xây dựng trên các
hệ lightweight container khác có thể tích hợp và cùng làm việc với
JGentle một cách dễ dàng.