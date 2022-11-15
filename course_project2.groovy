pipeline {
    agent 'any'
    stages {
        stage('Create docker image') {
            steps {
                sh 'rm -rf folder_for_docker_file'
                dir ("folder_for_docker_file") {
                    sh 'echo $USER' // выыести пользователя
                    sh 'docker pull ubuntu:16.04' //скачать имэдж
                    sh 'git clone https://github.com/IshchukI/course_project.git'//скачать доккер файл
                    sh 'docker build --no-cache -f course_project/Dockerfile . -t course_proj' //из доккер файла создать имэдж
               }
            }
        }
        stage('Create docker container') {
            steps {
                sh 'docker stop $(docker ps -a -q)'  //остановить все кнотейнеры
                sh 'docker rm $(docker ps -a -q)' //удвлить все контейнеры
                sh 'docker run --name course_project_container -id course_proj' // создать контейнер из имєджа
            }
        }

        stage('Setup utils'){
            steps {
                sh 'docker exec course_project_container sh -c \"apt-get update\"'
                sh 'docker exec course_project_container sh -c \"apt-get -y install software-properties-common\"'
                sh 'docker exec course_project_container sh -c \"apt-get install -y python3.9\"'
                sh 'docker exec course_project_container sh -c \"apt-get install -y python3-pip\"'
                sh 'docker exec course_project_container sh -c \"apt-get install -y git\"'
                sh 'docker exec course_project_container sh -c \"apt-get install -y python3.5-venv\"'
                sh 'docker exec course_project_container sh -c \"pip3 install pytest\"'
                sh 'docker exec course_project_container sh -c \"apt-add-repository ppa:qameta/allure\"'
                sh 'docker exec course_project_container sh -c \"apt-get update\"'
                sh 'docker exec course_project_container sh -c \"apt-get install -y allure\"'
                sh 'docker exec course_project_container sh -c \"allure --version\"'


            }
        }

        stage('Run project'){
            steps {
                sh 'docker exec course_project_container sh -c \"git clone https://github.com/IshchukI/PlayWrite.git\"'
                sh 'docker exec course_project_container sh -c \"python -m venv PlayWrite/playwrite_venv\"'
                sh 'docker exec course_project_container sh -c \"cd PlayWrite/ && . playwrite_venv/bin/activate && pip3 install -e . && pip install -e src/ && python -m playwright install\"'
                sh 'docker exec course_project_container sh -c \"playwright install-deps\"'
                sh 'docker exec course_project_container sh -c \"apt-get install -y libnss3 libnspr4 libatk1.0-0 libatk-bridge2.0-0 libcups2 libdrm2 libxkbcommon0 libxcomposite1 libxdamage1 libxfixes3 libxrandr2 libgbm1 libpango-1.0-0 libcairo2 libasound2 libatspi2.0-0 libwayland-client0\"'
//               sh 'docker exec course_project_container sh -c \". PlayWrite/playwrite_venv/bin/activate\"'
//                sh 'docker exec course_project_container sh -c \"cd PlayWrite/ && pip3 install -e . && pip3 install -e src/ && python3 -m playwright install\"'
                sh 'docker exec course_project_container sh -c \"python3 -m pytest PlayWrite/test\"'

            }
        }
    }
}

 pip install --upgrade pip


        curl -o allure-2.7.0.tgz -Ls https://dl.bintray.com/qameta/generic/io/qameta/allure/allure/2.7.0/allure-2.7.0.tgz
        sudo tar -zxvf allure-2.7.0.tgz -C /opt/
        sudo ln -s /opt/allure-2.7.0/bin/allure /usr/bin/allure
        rm -rf allure-2.7.0.tgz
        allure --version












apt-get -y install software-properties-common

apt-get -y install curl
curl -o allure-2.13.8.tgz -OLs http://ports.ubuntu.com/pool/universe/a/allure/allure_0.8.3.0-3build3_arm64.deb


https://launchpad.net/~qameta/+archive/ubuntu/allure/+files/allure_2.4.1~trusty_all.deb

http://ports.ubuntu.com/pool/universe/a/allure/allure_0.8.3.0-3build3_arm64.deb
tar -zxvf allure_0.8.3.0-3build3_arm64.deb -C /opt/

apt-get install -y wget
apt-get install -y libnss3 libnspr4 libatk1.0-0 libatk-bridge2.0-0 libcups2 libdrm2 libxkbcommon0 libxcomposite1 libxdamage1 libxfixes3 libxrandr2 libgbm1 libpango-1.0-0 libcairo2 libasound2 libatspi2.0-0 libwayland-client0


python3 -m pytest test
docker exec -it course_project_container bash

docker exec course_project_container sh -c "python3 --version"
docker exec course_project_container sh -c "cd PlayWrite/ && pip3 install -e . && pip3 install -e src/"