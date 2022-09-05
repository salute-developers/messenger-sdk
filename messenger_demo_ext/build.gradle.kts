/**
 * ВНИМАНИЕ! УВАГА! ATTENTION! ACHTUNG! ATTENZIONE!
 * Разработчикам SberDevices и всем, кто хочет поправить этот код
 * Этот модуль представляет собой пример ВНЕШНЕГО проекта, использующего SDK Мессенджера,
 * поэтому сюда НЕЛЬЗЯ добавлять НИКАКИХ ВНУТРЕННИХ зависимостей (т.е. зависимостей на проекты внутри репозитория)
 * Если вам кажется что сюда что-то нужно добавить - вы явно делаете что-то не так -
 * обратитесь за консультацией к команде Голосового Ассистента. Спасибо за внимание!
 * ВНИМАНИЕ! УВАГА! ATTENTION! ACHTUNG! ATTENZIONE!
 */
plugins {
    // Мы здесь подключаем SDK напрямую в модуль приложения,
    // но ничто не мешает подключить его и в library-модуль.
    id("com.android.application")
    // Мы все пишем на Kotlin, данное демо-приложение - не исключение.
    // В целевом приложении этот плагин не обязателен не обязательно.
    id("kotlin-android")
    // Для построения списка зависимостей ./gradlew :messenger_demo_ext:htmlDependencyReport
    // Список зависимостей можно посмотреть в репорте в секции releaseCompileClasspath
    id("project-report")
    id("kotlin-kapt")
}

repositories {
    // Здесь также как и в корневом build.gradle,
    // указаны ссылки на внутренние прокси к mavenCentral и google-репозиториям,
    // и также указана ссылка на локальный репозиторий во внутреннем проекте.
    // Точно также, этот блок требует замены для целевого проекта.
    maven { url = uri("https://nexus.sigma.sbrf.ru/nexus/content/repositories/central/") }
    maven { url = uri("https://nexus.sigma.sbrf.ru/nexus/content/repositories/maven_google_proxy/") }
    maven { url = uri("https://nexus.sigma.sbrf.ru/nexus/content/groups/public/") }
    // подключите следующие репозитории в целевом проекте
    // mavenCentral()
    // google()
    // maven { url = uri("https://jitpack.io") }
    maven {
        url = uri("${rootProject.projectDir}/repo")
        isAllowInsecureProtocol = true
    }
}

android {
    compileSdkVersion(32) // На усмотрение хост-приложения.
    buildToolsVersion("32.0.0") // На усмотрение хост-приложения.

    defaultConfig {
        targetSdkVersion(32) // На усмотрение хост-приложения.
        minSdkVersion(23) // минимальное API, которое поддерживает Messenger SDK. Можно выставить любое значение >= 23
        versionName = "Demo version"
        versionCode = 1
    }

    // SDK скомпилировано с Java 8, поэтому тут это тоже требуется указать.
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    // Этот блок в целом не обязателен, но в SDK используется jvmTarget для kotlin 1.8.
    // Лучше везде использовать согласованную версию.
    kotlinOptions {
        jvmTarget = "1.8"
    }

    lint {
        baseline = file("lint-baseline.xml")
        abortOnError = false
    }

    // View binding используется в SDK. Его необходимо включить.
    buildFeatures {
        viewBinding = true
    }

    packagingOptions {
        resources.pickFirsts.add("META-INF/kotlinx_coroutines_core.version")
        resources.pickFirsts.add("META-INF/kotlinx_coroutines_test.version")
    }
}

dependencies {
    // версию 1.0.0.9999 нужно будет заменить на ту, что предоставит команда Messenger SDK
    implementation("ru.sberbank.sdakit.android:mp_messenger-sdk_ext:1.0.0.9999")
    implementation("ru.sberbank.sdakit.android:core-ext:1.0.0.9999")
    // если шрифт Sber Sans не нужен, то этот артефакт можно отключить
    implementation("ru.sberbank.sdakit.android:core-font:1.0.0.9999")

    implementation("com.google.dagger:dagger:2.40.5")
    kapt("com.google.dagger:dagger-compiler:2.40.5")
}
