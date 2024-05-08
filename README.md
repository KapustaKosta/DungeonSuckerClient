# Dungeon sucker
*2024*
Авторы:
Кирилл Довганюк, Б05-311
Константин Стариков, Б05-311
Илья Капуткин, Б05-311

### Краткое описание игры
Dungeon Sucker — это пошаговый рогалик с элементами кооперативного прохождения лабиринта, где игроки объединяются для исследования комнат, борьбы с врагами и победы над боссами.

### Что из себя представляет игра на данный момент?
На данный момент игра почти готова. Реализовано:
1. Консольная версия боя
2. UI версия боя
3. UI передвижения по карте
4. Базовый интерфейс игры: окно с картой, окно инвентаря и основное окно с боем 

Реализованные механики:
- Случайная генерация уровня
- Усиление врагов в зависимости от уровня и времени, прошедшего с момента захода в уровень
- Генерация различных типов оружий, имеющих различные характеристики
- Генерация сокровищ со случайными наградами
### Требование к ПО
 -  Java 11-18
Сторонние библиотеки:
- com.badlogic.gdx  
### Команды для сборки проекта
Для сборки должна быть установлена JDK от 11 версии до 18. Проект разработчиками проверяется только на JDK 17 версии, так что рекомендуется использовать её.
Итак:
1. Склонируйте проект на свой пк:
`git clone https://gitlab.akhcheck.ru/tp2024-projects/project-7.git`
2. Зайдите в папку с проектом
`cd project-7`
3. Откройте файл gradle.properties
`gedit gradle.properties`
4. Добавьте строчку
`org.gradle.java.home=путь до вашего JDK`
Например в моём случае это:
org.gradle.java.home=C\:\\Program Files\\Java\\jdk-17
5. Теперь введите следующую команду:
`gradlew desktop:build`

Проект должен собраться
### Команды для запуска проекта
Для запуска должна быть установлена JDK от 11 версии до 18. Проект разработчиками проверяется только на JDK 17 версии, так что рекомендуется использовать её.
Итак:
1. Склонируйте проект на свой пк:
`git clone https://gitlab.akhcheck.ru/tp2024-projects/project-7.git`
2. Зайдите в папку с проектом
`cd project-7`
3. Откройте файл gradle.properties
`gedit gradle.properties`
4. Добавьте строчку
`org.gradle.java.home=путь до вашего JDK`
Например в моём случае это:
`org.gradle.java.home=C\:\\Program Files\\Java\\jdk-17`
5. Теперь введите следующую команду:
`gradlew desktop:run`
Проект должен запуститься

### Архитектура
Идейная UML диаграмма представлена на картинке uml-architecture.png. Классы разделены на три группы: геймплей, UI и сетевое взаимодействие.
Также есть более расширенная и актуальная диаграмма core.drawio.png 

### Дополнительно
Можно протестировать работу боёвки в консоли следующим образом:
Откройте файл *core/src/com/mipt/tp/dungeon_sucker/DungeonSuckerGame.java*.
Найдите строчку:
`private static final boolean testFight = false;`
Установите значение поля *testFight* в true. То есть:
`private static final boolean testFight = true;`
### Основные ссылки
[https://libgdx.com/](https://libgdx.com/) - сайт основной сторонней библиотеки
