def remoteBranch = "master"
def remoteURL = "https://github.com/Refly1108/springbootdemo.git"
pipelineJob('DSL SEND TEST') {

    description('my dsl test pipeline Job')
    logRotator {
        numToKeep(5)
        artifactNumToKeep(1)
        daysToKeep(-1)
        artifactNumToKeep(-1)
    }
    parameters {
        stringParam('branch', remoteBranch, 'the branch to build')
        choiceParam('ENV', ['DEV', 'UAT', 'PROD'],'the enc to deploy')
    }
    definition {
        cpsScm {
            scm {
                git{
                    remote{
                        url(remoteURL)
                    }
                    branches(remoteBranch)
                    scriptPath("testDLS.groovy")
                    extensions{}

                }
            }
        }
    }
}