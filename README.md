# Задание  практической работы
## Описание требований к программе

Необходимо написать приложение, реализующее вычисление математических выражений.

##### Требования к программе:

На вход будет подаваться строка, содержащая цифры и знаки сложения/вычитания/деления/умножения, например, 4.2 + 2 * 3 / 3 - 6.1.
Необходимо разобрать строку и вычислить результат математического выражения или сообщить об ошибке.

Выражение должно вводиться с клавиатуры через консоль.
Допускается хранение исходной строки в переменной типа String.

##### Кейсы, которые необходимо учесть:

* В веденной строке с выражением не должно встречаться ничего, кроме цифр и знаков математических операций, в противном случае вы должны выбрасывать исключение и выводить понятное пользователю сообщение об ошибке.
* Числа в выражении могут быть дробными. Используйте разделитель ..
* При недопустимой операции, например деление на 0, вы должны выбрасывать исключение, и выводить сообщение об ошибке.
* Не допускается введение некорректного выражения вида 4++2. Т.е. не может быть дублирующихся символов операций.
* В выражении нельзя использовать скобки и соответственно, нельзя использовать отрицательные числа. Выражение вида -2+4 также считаем недопустимым.
