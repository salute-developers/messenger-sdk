cd ../../..
./gradlew --stop
./gradlew :core:ext:build --rerun-tasks -PenableFatAar=true
./gradlew :core:ext:publish -PenableFatAar=true -Pnexus.releases.repo.url="$(pwd)/../android/mp/messenger/demo_ext/repo" -Pnexus.need.credentials=false -Pversion.properties="non/existent/path/to/version.properties" -Pnexus.snapshot=false
./gradlew :core:font:build --rerun-tasks -PenableFatAar=true
./gradlew :core:font:publish -PenableFatAar=true -Pnexus.releases.repo.url="$(pwd)/../android/mp/messenger/demo_ext/repo" -Pnexus.need.credentials=false -Pversion.properties="non/existent/path/to/version.properties" -Pnexus.snapshot=false
./gradlew --stop
./gradlew :mp_messenger:sdk_ext:build --rerun-tasks -PenableFatAar=true
# /Users/a18202247/src/staros22/staros/android/build-system/libraries/version.properties - путь для файла с версией
./gradlew :mp_messenger:sdk_ext:publish -PenableFatAar=true -Pnexus.releases.repo.url="$(pwd)/mp/messenger/demo_ext/repo" -Pnexus.need.credentials=false -Pversion.properties="non/existent/path/to/version.properties" -Pnexus.snapshot=false

cd mp/messenger/demo_ext
./gradlew build