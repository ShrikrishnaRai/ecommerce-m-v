export default {
    watch: {
        dialog (val) {
            val || this.closeDialogBox()
        },
    },
    methods: {
        getFormTitle (persistIndex, componentName) {
                return this.persistIndex === -1 ? `Insert ${componentName} Information` : `Edit ${componentName}`
            },
        getPersistTitle () {
            return this.persistIndex === -1 ? 'Save' : 'Save Changes'
        },
        resetDialogField () {
            this.persistIndex = -1
            this.temp = Object.assign({}, this.defaultItem)
        },
        closeDialogBox () {
            this.dialog = false
            this.persistIndex = -1
            this.resetDialogField()
        },
        logOut () {
            this.$router.push('/')
            if (localStorage.getItem('user-role') === 'user') {
                localStorage.removeItem('user-id')
                localStorage.removeItem('user-name')
                localStorage.removeItem('user-profile-image')
            }
             localStorage.removeItem('user-role')
        },
        getLoggedInUserProfileImage () {
            return localStorage.getItem('user-profile-image')
        },
        getLoggedInUserName () {
            return localStorage.getItem('user-name')
        },
        getLoggedInUserId () {
            return localStorage.getItem('user-id')
        },
        pushToHomePage () {
            this.$router.push('/bazzar')
        },
        calculateReview (reviewArray) {
            return reviewArray % reviewArray.size()
        },
    },
}
