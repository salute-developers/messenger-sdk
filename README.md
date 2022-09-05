## Демо-приложение Мессенджера для внешних потребителей (внутренняя инструкция)

Менять код здесь следует с большой осторожностью. Многие зависимости тут захардкожены, чтобы проект не зависел ни от каких внутренностей.
Если вы не уверены в своих действиях, то скорее всего вам не нужно трогать этот проект, и лучше обратиться за помощью к команде Messenger SDK.

### Локальная сборка

Для того чтобы собрать локальное демо, нужно сделать следующие шаги:

1. Собрать внешние артефакты. Для этого в основном проекте мессенджера выполнить:
```sh
# собрать core-артефакт для внешнего проекта и опубликовать его в локальный репозиторий. Путь к version.properties специально делаем несуществующим,
# чтобы прописалась версия 1.0.0 - именно такая версия прошита в демо-приложении. Это сделано, чтобы внешнее демо не зависело ни от чего, в т.ч. от билд-системы
# ускорить последующую сборку
./gradlew --stop 
cd sdakit
./gradlew :core:ext:build --rerun-tasks -PenableFatAar=true
./gradlew :core:ext:publish -PenableFatAar=true -Pnexus.releases.repo.url="$(pwd)/../android/mp/messenger/demo_ext/repo" -Pnexus.need.credentials=false -Pversion.properties="non/existent/path/to/version.properties" -Pnexus.snapshot=false
# собрать core-артефакт шрифта для внешнего проекта и опубликовать его в локальный репозиторий. Аналогично с version.properties
./gradlew :core:font:build --rerun-tasks -PenableFatAar=true
./gradlew :core:font:publish -PenableFatAar=true -Pnexus.releases.repo.url="$(pwd)/../android/mp/messenger/demo_ext/repo" -Pnexus.need.credentials=false -Pversion.properties="non/existent/path/to/version.properties" -Pnexus.snapshot=false
# собрать артефакт мессенджера для внешнего проекта и опубликовать его в локальный репозиторий. Аналогично с version.properties
cd ../android
./gradlew :mp_messenger:sdk_ext:build --rerun-tasks -PenableFatAar=true
# /Users/a18202247/src/staros22/staros/android/build-system/libraries/version.properties - путь для файла с версией
./gradlew :mp_messenger:sdk_ext:publish -PenableFatAar=true -Pnexus.releases.repo.url="$(pwd)/mp/messenger/demo_ext/repo" -Pnexus.need.credentials=false -Pversion.properties="non/existent/path/to/version.properties" -Pnexus.snapshot=false
# если возникнут проблемы с командой сверху можно попробовать эту
cd mp/messenger
./gradlew :sdk_ext:publish -PenableFatAar=true -Pnexus.releases.repo.url="$(pwd)/demo_ext/repo" -Pnexus.need.credentials=false -Pversion.properties="non/existent/path/to/version.properties" -Pnexus.snapshot=false
```
2. Если нужно проверить обфускацию, то нужно собрать обфусцированные артефакты. Для этого следует дополнительно передать флаг `-Pexternal.sdk=true` в команды выше.

3. Открыть в Android Studio проект в папке `android/mp/messenger/demo_ext`. Проект должен синхронизироваться успешно, после чего можно запускать приложение для проверки.

4. Также можно собрать внешнее демо из консоли:
```sh
cd ../android/mp/messenger/demo_ext
./gradlew build
```
