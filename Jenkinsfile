pipeline {
    agent any

    // Use Jenkins environment variables
    environment {
        PROD_CLIENT_APP_CREDENTIALS = credentials('prod-client-app-credentials')
        QA_CLIENT_APP_CREDENTIALS = credentials('qa-client-app-credentials')
    }

    parameters {
        string(name: 'RELEASE', defaultValue: 'v1.0', description: 'Project Release')
        choice(name: 'ENVIRONMENT', choices: ['PROD', 'QA'], description: 'Test Environment')
        choice(name: 'TEST_SUITE', choices: ['REGRESSION', 'PIPELINE_1'], description: 'Test suite to execute')
    }

    stages {
        stage('Clean') {
            steps {
                setParamsAsEnvironmentVars()
                clean()
            }
        }
        stage('Test') {
            steps {
                setParamsAsEnvironmentVars()
                test()
            }
        }
    }

    post {
        always {
            publishHTML(target: [
                    allowMissing         : false,
                    alwaysLinkToLastBuild: true,
                    keepAll              : true,
                    reportDir            : "build/reports/tests/$TEST_SUITE_COMMAND/",
                    reportFiles          : 'index.html',
                    reportName           : "Test Results"
            ])
        }
        failure {
            script {
                if (env.BRANCH_NAME == 'master') {
                    sh 'echo start - ZIP'
                    zip zipFile: "${TEST_SUITE_COMMAND}.zip", archive: false, dir: "build/reports/tests/$TEST_SUITE_COMMAND/"
                    sh 'echo end - ZIP'
                    emailext(
                            attachmentsPattern: "${TEST_SUITE_COMMAND}.zip",
                            to: '$DEFAULT_RECIPIENTS',
                            subject: "Jenkins Build ${currentBuild.currentResult} on $TEST_SUITE_COMMAND - $PROFILE Environment",
                            body: "Build failed ${env.JOB_NAME} build no: ${env.BUILD_NUMBER}.<br><br>" +
                                    "View detail results here:<br> <a href='${env.BUILD_URL}Test_20Results/'>" +
                                    "${env.BUILD_URL}Test_20Results/</a><br><br>" +
                                    "Blue Ocean:<br><a href='${env.RUN_DISPLAY_URL}'>${env.RUN_DISPLAY_URL}</a> <br><br> <b>*** This is an automatically generated email, please do not reply.</b>")
                }
            }
        }
    }
}

void setParamsAsEnvironmentVars() {
    script {
        //Pass Global DATA
        env.PROFILE = params.ENVIRONMENT
        env.RELEASE = params.RELEASE

        //Pass environment specific DATA
        switch (env.PROFILE) {
            case 'PROD':
                echo 'Setting PROD profile...'
                env.CLIENT_ID = env.PROD_CLIENT_APP_CREDENTIALS_USR
                env.CLIENT_SECRET = env.PROD_CLIENT_APP_CREDENTIALS_PSW
                break
            case 'QA':
                echo 'Setting QA profile...'
                env.CLIENT_ID = env.QA_CLIENT_APP_CREDENTIALS_USR
                env.CLIENT_SECRET = env.QA_CLIENT_APP_CREDENTIALS_PSW
                break
        }

        //Test suite you which you want to execute
        switch (params.TEST_SUITE) {
            case 'REGRESSION':
                env.TEST_SUITE_COMMAND = "regressionTest"
                break
            case 'PIPELINE_1':
                env.TEST_SUITE_COMMAND = "pipeLine1_Test"
                break
        }
    }

    sh """
        echo PROFILE: $PROFILE
        echo RELEASE: $RELEASE
        echo TEST_SUITE_COMMAND: $TEST_SUITE_COMMAND
        """
}

void test() {
    sh './gradlew clean $TEST_SUITE_COMMAND -Pprofile=$ENVIRONMENT -Prelease=$RELEASE'
}

void clean() {
    sh './gradlew clean'
}
