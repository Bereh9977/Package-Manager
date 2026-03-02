# Package-Manager

1) Проєкт використовує Maven, тому всі залежності завантажуються командою mvn.
Потрібно, щоб було встановлено:
- Java JDK 17 або новіша
- Apache Maven 3.9+

Для завантаження залежностей виконайте команду в корені проєкту (за допомогою cmd):
- mvn package

2) Запуск виконується за допомогою компілятора в середовищі IntelliJ IDEA.

3) Для перевірки стилю коду використовується Checkstyle та тип кодування Google Style, відкрийте cmd в проекті, поряд з файлом pom.xml та виконайте команду:
- mvn checkstyle:check

<img width="1005" height="494" alt="image" src="https://github.com/user-attachments/assets/0d94c362-c8ac-4db9-a570-6b825a05e334" />
