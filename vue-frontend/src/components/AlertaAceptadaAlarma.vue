<template>
    <div class="container" v-if="!requestCompleteAcceptAlerta">
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
                <td><button @click="acceptAlerta">Aceptar Alerta</button></td>
            </tr>
        </table>
    </div>

    <div v-if="requestCompleteAcceptAlerta">
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
            alertas: [],
            token: '',
            requestCompleteAcceptAlerta: false,
            id: '',
        }
    },
    methods: {
        async acceptAlerta() {
            try {
                this.alertas = await AlertaService.acceptAlerta(this.id,this.token)
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