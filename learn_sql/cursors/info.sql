1. Объявить курсор – используется команда DECLARE имя_курсора;
2. Открыть курсор – используется команда OPEN имя_курсора;
3. Чтение следующей строки из курсора – используется команда FETCH имя_курсора;
4. Закрыть курсор – используется команда CLOSE имя_курсора;
5. Удалить курсор из памяти – используется команда DEALLOCATE имя_курсора.

DECLARE [cursor_name] [[NO] SCROLL] CURSOR FOR [query];
DECLARE curs_products cursor for select * from products;

FETCH [FORWARD | BACKWARD] [direction (rows)] FROM [cursor_name];
next, prior, forward, backward, first, last, absolut count, relative count
FETCH NEXT FROM cursor_products;

MOVE [FORWARD | BACKWARD] [direction (rows)] FROM [cursor_name];
MOVE FORWARD 2 FROM cursor_products;

CLOSE cursor_name;
CLOSE cursor_products;
COMMIT;
