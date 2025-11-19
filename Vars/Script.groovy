def ReleaseCut() {
    def tagName = "T1.0.${env.BUILD_NUMBER}"
    def tagMsg = "Release Tag ${env.BUILD_NUMBER}"
    withCredentials([string(credentialsId: 'git-secret-id', variable: 'TOKEN')]

    ) {
        bat """
                                git config user.email "jenkins@ci.com"
                                git config user.name "jenkins"

                                git remote set-url origin https://${TOKEN}@github.com/Sushanta121/book-keeping.git

                                git checkout main
                                git pull origin main

                                git tag -a ${tagName} -m "${tagMsg}"
                                git push origin ${tagName}
                            """

    }
}