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
                <td>ID Orden:</td>
                <td><input type="text" v-model="id" /></td>
            </tr>
            <tr>
                <td><button @click="getAlerta">Mostrar Alerta</button></td>
            </tr>
        </table>
    </div>


    <div class="container">
        <h1 class="text-center"> Ultima Alarma </h1>

        <table class="table table-striped table-bordered">
            <thead>
                <th>ID Asociada</th>
                <th>Estado</th>
                <th>Aceptada por Usuario</th>
                <th>Observaciones</th>
                <th>Fecha de Aceptacion</th>
            </thead>
            <tbody>
                <tr>
                    <td> {{ id }} </td>
                    <td> {{ estado }} </td>
                    <td> {{ usrAceptador }} </td>
                    <td> {{ observaciones }} </td>
                    <td> {{ fechaDeAceptacion }} </td>
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
            token: '',
            requestCompleteGetAlerta: false,
            id: '',
        }
    },
    methods: {
        async getAlerta() {
            try {
                const result = await AlertaService.getAlerta(this.id, this.token)
                this.id = result.id;
                this.estado = result.estado;
                this.usrAceptador = result.usrAceptador;
                this.observaciones = result.observaciones;
                this.fechaDeAceptacion = result.fechaDeAceptacion;
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