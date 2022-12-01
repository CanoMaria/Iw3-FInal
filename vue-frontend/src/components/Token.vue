<template>
    <button id="id_button_token" @click="getTokens">Solicitar Token</button>

        <label for="input_username">Usuario: </label>
        <input type="text" id="input_username" v-model="username">

        <label for="input_password">Constrasenia: </label>
        <input type="password" id="input_password" v-model="password">
   
</template>

<script>
import TokenService from '@/services/TokenService';

    export default{
        name: '',

        data(){
            return {
                username: '',
                password: ''
            }
        },
        methods:{
            getTokens(){
                TokenService.getTokens(this.username,this.password)
            }
        },

        // created(){
        // }

    }

</script>

<style>

</style>