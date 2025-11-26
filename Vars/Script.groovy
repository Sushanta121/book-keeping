node(lable: 'TEST'){
    booleanParam(defaultValue: true, description: '', name:'DRY_RUN'),
    choiceParam(choices: "Minor\nMinor with Tags\nPatch\nPatch with Tag", description: '<pre>Minor:<br>  Release of one or more projects created from each project\'s master branch. All<br>  dependencies will use their latest released versions, created prior to or during<br>  this release. Increments dev\'s minor version.<br><br>Minor with Tags:<br>  Changes the Minor release to create from cherry-picked changes on top of each<br>  project\'s latest tag instead of the master branch.<br><br>Patch:<br>  A patch release of projects created from cherry-picked changes on top of each<br>  project\'s latest tag. Dependencies will be updated to the latest versions.<br>  Does not affect dev\'s version.<br><br>Patch with Tag:<br>  Changes the Patch release to release a single project and manually specify the tag<br>  on which the project is built. Any change to dependencies in the POM must be made<br>  in the release branches, manually. The patch versions of the release project will<br>  be a patch increment of the provided tag. A second release will be a patch<br>  increment of the latest minor release. This release method must be used when patch<br>  releasing a project for which a minor release has already produced a greater minor<br>  version. Must specify tag in SOURCE_TAG.</pre>', name:'WORKFLOW'),
    string(defaultValue: '', description: 'Tag on which Patch with Tag will be created.', name: 'SOURCE_TAG'),
    booleanParam(defaultValue: false, description: '', name:'PREPARE'),
    booleanParam(defaultValue: false, description: '', name:'RELEASE'),
    booleanParam(defaultValue: false, description: '', name:'CLEAN'),
    text(defaultValue: '', description: 'List of catalogs to be picked for version freeze(Comma seperated value)', name: 'CATALOG_FREEZE'),
    text(defaultValue: '', description: 'List of catalogs to be picked for version Unfreeze(Comma seperated value)', name: 'CATALOG_UNFREEZE'),
    text(defaultValue: '', description: 'Version of catalogs to be picked for version freeze(Comma Separated Values, order of catalog versions should be same as in Catalog Freeze)', name: 'CATALOG_VERSION'),
    text(defaultValue: '', description: 'List of Projects to be released.', name: 'PROJECTS'),
    string(defaultValue: '', description: 'Time in UTC, required for MINOR releases that include PREPARE. Example: 2019-05-03T20:00:00.000Z', name: 'TIME'),
    string(defaultValue: '', description: 'Release Review JIRA. Ex: PHPROGRAM-1234', name: 'RELEASE_JIRA'),
    string(defaultValue: '', description: 'Release Version (Optional)', name: 'RTP')
}


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
return this