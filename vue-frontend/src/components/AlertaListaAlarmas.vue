<template>
    <div class="container" v-if="!requestCompleteGetAlerta">
        <table>
            <tr>
                <td>Token Generated:</td>
                <td><input id="input_token" type="text" v-model="token" /></td>
            </tr>

            <tr>
                <td><button @click="getAlertaList">Submit</button></td>
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
                <tr v-for="alarma in alarmas" v-bind:key="alarma.id">
                    
                    <td> {{ alarma.id }} </td>
                    <td> {{ alarma.estado }} </td>
                    <td> {{ alarma.usrAceptador }} </td>
                    <td> {{ alarma.observaciones }} </td>
                    <td> {{ alarma.fechaDeAceptacion }} </td>
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
                this.alertas = await AlertaService.getAlertaList(this.token)

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