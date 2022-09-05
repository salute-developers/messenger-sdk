# мы специально добавили артифакты с версией 1.0.0.9999 чтобы отработала таска messenger_demo_ext:htmlDependencyReport
# а теперь их удаляем, они не должны попасть в артифакт
rm -rf android/mp/messenger/demo_ext/repo/ru/sberbank/sdakit/android/core-ext/1.0.0.9999
rm -rf android/mp/messenger/demo_ext/repo/ru/sberbank/sdakit/android/core-font/1.0.0.9999
rm -rf android/mp/messenger/demo_ext/repo/ru/sberbank/sdakit/android/messenger-sdk_ext/1.0.0.9999
mkdir demo_ext
mkdir demo_ext/dependency_report
cp -R android/mp/messenger/demo_ext/* demo_ext/
cp -R android/mp/messenger/demo_ext/messenger_demo_ext/build/reports/project/dependencies/* demo_ext/dependency_report/
rm demo_ext/cacerts_nexus
rm demo_ext/README.md
mv demo_ext/README_external.md demo_ext/README.md
zip -r $1 demo_ext/ -x \*\.DS_Store -x \*/build/\* -x demo_ext/export-sdk-demo.sh

echo
echo "Exported sample app to archive $1"
echo
