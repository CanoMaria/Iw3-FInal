<template>
    <div class="container" v-if="!requestCompleteCrearOrdenes">
        <table>
            <tr>
                <td>Input Token Generated:</td>
                <td><input id="input_token" type="text" v-model="token" /></td>
            </tr>
            
            <tr>
                <td>Fecha de recepción:</td>
                <td><input type="date" v-model="nuevaOrden.fechaRecepcion" /></td>
            </tr>

            <tr>
                <td>Razón social del cliente:</td>
                <td><input type="text" v-model="nuevaOrden.cliente.razonSocial" /></td>
            </tr>

            <tr>
                <td>Contacto del cliente:</td>
                <td><input type="text" v-model="nuevaOrden.cliente.contacto" /></td>
            </tr>

            ...

            <tr>
                <td>Cisternado del camión:</td>
                <td><input type="text" v-model="nuevaOrden.camion.cisternado" /></td>
            </tr>

            <tr>
                <td>Descripción del camión:</td>
                <td><input type="text" v-model="nuevaOrden.camion.descripcion" /></td>
            </tr>

            <tr>
                <td>Patente del camión:</td>
                <td><input type="text" v-model="nuevaOrden.camion.patente" /></td>
            </tr>

            ...

            <tr>
                <td>Apellido del chofer:</td>
                <td><input type="text" v-model="nuevaOrden.chofer.apellido" /></td>
            </tr>

            <tr>
                <td>Documento del chofer:</td>
                <td><input type="text" v-model="nuevaOrden.chofer.documento" /></td>
            </tr>

            <tr>
                <td>Nombre del chofer:</td>
                <td><input type="text" v-model="nuevaOrden.chofer.nombre" /></td>
            </tr>

            ...

            <tr>
                <td>Email para Alerta:</td>
                <td><input type="text" v-model="nuevaOrden.alerta.mail" /></td>
            </tr>

            <tr>
                <td>Temperatura Max:</td>
                <td><input type="text" v-model="nuevaOrden.alerta.temperaturaMax" /></td>
            </tr>

            ...

            <tr>
                <td>Nombre del producto:</td>
                <td><input type="text" v-model="nuevaOrden.producto.nombre" /></td>
            </tr>

            <tr>
                <td>Descripción del producto:</td>
                <td><input type="text" v-model="nuevaOrden.producto.descripcion" /></td>
            </tr>
            ...

            <tr>
                <td>Código externo:</td>
                <td><input type="text" v-model="nuevaOrden.codigoExterno" /></td>
            </tr>

            <tr>
                <td><button @click="createOrden">Crear orden</button></td>
            </tr>
        </table>
    </div>
</template>

<script>
import OrdenService from '@/services/OrdenService';

export default {
    name: '',
    data() {
        return {
            token: '',
            requestCompleteCrearOrdenes: false,
            nuevaOrden: {
                "fechaRecepcion": "2022-11-18",
                "cliente": {
                    "razonSocial": "FedeRazonSocial",
                    "contacto": "3517736362"
                },
                "camion": {
                    "cisternado": ["111", "222", "333"],
                    "descripcion": "Fede Descr",
                    "patente": "CAM567041"
                },
                "chofer": {
                    "apellido": "Caba",
                    "documento": "37689712",
                    "nombre": "Fede"
                },
                "alerta": {
                    "mail": "[ayecano98@gmail.com]",
                    "temperaturaMax": "50"
                },
                "producto": {
                    "descripcion": "Fede Descr",
                    "nombre": "FedeProdu"
                },
                "codigoExterno": "900"
            }


        }
    },
    methods: {
        async createOrden() {
            try {
                // Obtener un token llamando al método "getTokens" importado

                // Llamar al método "createOrden" del servicio de orden, pasando la nueva orden y el token como parámetros
                this.nuevaOrden = await OrdenService.createOrden(this.nuevaOrden, this.token);
                console.log('Orden creada correctamente:', this.nuevaOrden);
                this.requestCompleteCrearOrdenes = true;
            } catch (error) {
                console.error(error);
            }
        }
    },
    // created(){
    //      this.getCamiones()
    // }

}

</script>

<style>
.container {
    margin: 0 auto;
}
</style>