<template>
    <div class="container" v-if="!requestCompleteGetAlerta">
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
                <td>Usuario Aceptador:</td>
                <td><input type="text" v-model="cargaDatos.usrAceptador" /></td>
            </tr>
            <tr>
                <td>observaciones:</td>
                <td><input type="text" v-model="cargaDatos.observaciones" /></td>
            </tr>
            <tr>
                <td><button @click="acceptAlerta">Aceptar Alerta</button></td>
            </tr>
        </table>
    </div>

    <div v-if="requestCompleteGetAlerta">
        <div class="alert alert-success" role="alert">
            <i class="fas fa-check-circle"></i> La petición se completó exitosamente
        </div>
    </div>

</template>

<script>
import AlertaService from '@/services/AlertaService';

export default {
    name: '',
    data() {
        return {
            alarmas: [],
            token: '',
            requestCompleteGetAlerta: false,
            id: '',
            cargaDatos: {
                "usrAceptador": "",
                "observaciones": ""
            },

        }
    },
    methods: {
        async acceptAlerta() {
            try {
                this.alarmas = await AlertaService.acceptAlerta(this.id, this.cargaDatos, this.token)

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