# github-search
Необходимо реализовать приложение для поиска по репозиториям сервиса https://github.com/, используя API (https://developer.github.com/v3/). Приложение состоит из двух экранов. Первый экран - авторизация. Пользователю предлагается авторизоваться, либо продолжить анонимно. После авторизации или пропуска авторизации пользователь попадает на экран с поиском по репозиториям.

На экране поиска в тулбаре название приложения, иконка поиска и overflow меню. При нажатии на поиск открывается SearchView. При вводе символов подгружается результат поиска и отображается список. В каждом элементе списка должны присутствовать аватара владельца репозитория, полное название репозитория и его описание. Остальные поля можно добавить по желанию исполнителя. При скроллинге данные должны догружаться (ленивая подгрузка). В overflow меню, в зависимости от статуса авторизации пользователя находится кнопка "Войти" или "Выйти".

Прогресс и ошибки загрузки данных должны быть визуально отображены. Поворот экрана должен быть включен и корректно обработан. Исходный код должен быть на Java.