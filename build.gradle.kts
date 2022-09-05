/**
 * ВНИМАНИЕ! УВАГА! ATTENTION! ACHTUNG! ATTENZIONE!
 * Разработчикам SberDevices и всем, кто хочет поправить этот код
 * Этот модуль представляет собой пример ВНЕШНЕГО проекта, использующего SDK Мессенджера,
 * поэтому сюда НЕЛЬЗЯ добавлять НИКАКИХ ВНУТРЕННИХ зависимостей (т.е. зависимостей на проекты внутри репозитория)
 * Если вам кажется что сюда что-то нужно добавить - вы явно делаете что-то не так -
 * обратитесь за консультацией к команде Мессенджера. Спасибо за внимание!
 * ВНИМАНИЕ! УВАГА! ATTENTION! ACHTUNG! ATTENZIONE!
 */
buildscript {
    repositories {
        // Здесь указаны ссылки на внутренние прокси к mavenCentral и google-репозиториям,
        // а также ссылка на локальный репозиторий во внутреннем проекте, в который складывается SDK.
        // Для сборки проекта вне сберовской инфраструктуры, вам нужно заменить эти ссылки прямыми ссылками
        // на mavenCentral/google, или указать свои прокси. А также поменять ссылку на репозиторий,
        // из которого загружается SDK (это также может быть локальная папка в вашем проекте).
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
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}
