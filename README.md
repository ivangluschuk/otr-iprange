Тестовое задание для компаии ОТР.

На вход программе задаются два IP адреса, программа перебирает все допустимые адреса в введенном диапазоне и выдает их на экран.

Пример:
пользователь вводит с клавиатуры
192.168.0.1 и 192.168.0.5

Программа должна выдать:

192.168.0.2<br/>
192.168.0.3<br/>
192.168.0.4

Для запуска программы выполните следующие команды:
1. git clone https://github.com/ivangluschuk/otr-iprange
2. cd otr-iprange
3. mvn package
4. java -jar target/otr-iprange-1.0-SNAPSHOT.jar
