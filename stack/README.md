# Стек без блокировок

## Описание
Проект включает в себя следующие исходные файлы:

* `src/main/java/ru/ifmo/pp/Bank.java` содержит интерфейс стека.

## Задание
Необходимо доработать реализую `Stack` так, чтобы она стала безопасной для использования из множества потоков одновременно.

## Сборка и тестирование


Обратите внимание, что исходная реализация проходит только `FunctionalTest`, но не проходит многопоточные тесты. При этом, прохождения тестов недостаточно для зачета за это задание. Тесты будут проходить, если у каждого метода класса `BankImpl` написать ключевое слово `synchronized` (проверьте!). Но такая реализации, несмотря на прохождение тестов, не удовлетворяет заданию, которое требует применение тонкой, а не грубой, блокировки.