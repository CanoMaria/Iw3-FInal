<template>
    <div class="container" v-if="!requestCompleteGetAlerta">
        <div class="alert alert-info" role="alert">
            Usar esta categoria de Requests una vez cargadas las temperaturas
        </div>
        <table>
            <tr>
                <td>Token Generated:</td>
                <td><input id="input_token" type="text" v-model="token" /></td>
            </tr>
            <tr>
                <td>ID:</td>
                <td><input id="input_id" type="text" v-model="id" /></td>
            </tr>
            <tr>
                <td><button @click="getAlertaList">Listar Alertas</button></td>
            </tr>
        </table>
    </div>

    <div class="container">
        <h1 class="text-center"> Lista de Alarmas </h1>

        <table class="table table-striped table-bordered">
            <thead>
                <th>ID Asociada</th>
                <th>Estado</th>
                <th>Aceptada por Usuario</th>
                <th>Observaciones</th>
                <th>Fecha de Aceptacion</th>
            </thead>
            <tbody>
                <tr v-for="alerta in alertas" v-bind:key="alerta.id">
                    
                    <td> {{ alerta.id }} </td>
                    <td> {{ alerta.estado }} </td>
                    <td> {{ alerta.usrAceptador }} </td>
                    <td> {{ alerta.observaciones }} </td>
                    <td> {{ alerta.fechaDeAceptacion }} </td>
                </tr>
            </tbody>
        </table>
    </div>

</template>

<script>
import AlertaService from '@/services/AlertaService';

export default {
    name: '',
    data() {
        return {
            alertas: [],
            token: '',
            requestCompleteGetAlerta: false,
            id: '',
        }
    },
    methods: {
        async getAlertaList() {
            try {
                this.alertas = await AlertaService.getAlertaList(this.id,this.token)
                this.requestCompleteGetAlerta = true;
            } catch (error) {
                console.error(error);
            }
        }
    },

}

</script>

<style>

</style>