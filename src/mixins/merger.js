export default {
    methods: {
        getProductMergerListFromProductStore () {
            return this.$store.state.product.productMergerList
        },
    },
}
