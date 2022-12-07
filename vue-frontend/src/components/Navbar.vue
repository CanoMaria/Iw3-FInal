<!-- Navbar.vue -->


<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <a class="navbar-brand" href="#">Navbar</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent" style="background-color: #257;">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link" href="#" id="navbarDropdown" role="button" aria-haspopup="true" aria-expanded="false"
              @click="selectComponent('Login')">
              Generate Token
            </a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="#" id="navbarDropdown" role="button" aria-haspopup="true" aria-expanded="false"
              @click="selectComponent('Camion')">
              Camion
            </a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="#" id="navbarDropdown" role="button" aria-haspopup="true" aria-expanded="false"
              @click="selectComponent('Chofer')">
              Chofer
            </a>
          </li>

          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
              aria-haspopup="true" aria-expanded="false">
              Orden Requests
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
              <a class="nav-link" href="#" id="navbarDropdown" role="button" aria-haspopup="true" aria-expanded="false"
                @click="selectComponent('Orden')">
                List Ordenes
              </a>
              <div class="dropdown-divider"></div>
              <a class="nav-link" href="#" id="navbarDropdown" role="button" aria-haspopup="true" aria-expanded="false"
                @click="selectComponent('OrdenById')">
                Search By ID
              </a>
              <div class="dropdown-divider"></div>
              <a class="nav-link" href="#" id="navbarDropdown" role="button" aria-haspopup="true" aria-expanded="false"
                @click="selectComponent('OrdenByEstado')">
                Search By Estado
              </a>
              <div class="dropdown-divider"></div>
              <a class="nav-link" href="#" id="navbarDropdown" role="button" aria-haspopup="true" aria-expanded="false"
                @click="selectComponent('OrdenCreate')">
                Create >1
              </a>
              <div class="dropdown-divider"></div>
              <a class="nav-link" href="#" id="navbarDropdown" role="button" aria-haspopup="true" aria-expanded="false"
                @click="selectComponent('OrdenPesajeInicial')">
                Pesaje Inicial >2.1
              </a>
              <div class="dropdown-divider"></div>
              <a class="nav-link" href="#" id="navbarDropdown" role="button" aria-haspopup="true" aria-expanded="false"
                @click="selectComponent('OrdenCargaDatos')">
                Carga de Datos >2.2
              </a>
              <div class="dropdown-divider"></div>
              <a class="nav-link" href="#" id="navbarDropdown" role="button" aria-haspopup="true" aria-expanded="false"
                @click="selectComponent('OrdenCierre')">
                Cierre >3
              </a>
              <div class="dropdown-divider"></div>
              <a class="nav-link" href="#" id="navbarDropdown" role="button" aria-haspopup="true" aria-expanded="false"
                @click="selectComponent('OrdenPesajeFinal')">
                Pesaje Final >4
              </a>
              <div class="dropdown-divider"></div>
              <a class="nav-link" href="#" id="navbarDropdown" role="button" aria-haspopup="true" aria-expanded="false"
                @click="selectComponent('OrdenConciliacion')">
                Conciliacion >5
              </a>

            </div>

          </li>

          <li class="nav-item active">
            <a class="nav-link" href="#" id="navbarDropdown" role="button" aria-haspopup="true" aria-expanded="false"
              @click="selectComponent('Producto')">
              Producto
            </a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="#" id="navbarDropdown" role="button" aria-haspopup="true" aria-expanded="false"
              @click="selectComponent('Producto')">
              Alerta: Alarma
            </a>
          </li>

        </ul>

      </div>
    </nav>

    <!-- llamar a los componentes aquí -->
    <!-- <Camion /> -->
    <!-- <Chofer />
    <Orden /> -->
  </div>
</template>

<!-- <template>
  <nav>
    <a @click="selectComponent('Camion')">Sección 1</a>
    <a @click="selectComponent('Orden')">Sección 2</a>
    <a @click="selectComponent('Chofer')">Sección 3</a>
  </nav>
</template> -->

<script>
import Login from '@/components/Login.vue';
import Camion from '@/components/Camion.vue';
import Chofer from '@/components/Chofer.vue';
import Orden from '@/components/Orden.vue';
import OrdenById from '@/components/OrdenById.vue';
import OrdenCreate from '@/components/OrdenCreate.vue';
import Producto from '@/components/Producto.vue';
import OrdenByEstado from '@/components/OrdenByEstado.vue';
import OrdenPesajeInicial from '@/components/OrdenPesajeInicial.vue';
import OrdenPesajeFinal from '@/components/OrdenPesajeFinal.vue';
import OrdenCargaDatos from '@/components/OrdenCargaDatos.vue';
import OrdenCierre from '@/components/OrdenCierre.vue';
import OrdenConciliacion from '@/components/OrdenConciliacion.vue';AlertaAlarma
import AlertaAlarma from '@/components/AlertaAlarma.vue';


export default {
  name: "Navbar",
  methods: {
    // Esta función emitirá un evento para que el componente padre (MainPage) cambie el componente cargado
    selectComponent(componentName) {
      this.$emit("selectComponent", componentName);
    },
  },
  components: { Login, Camion, Chofer, Orden, OrdenById, OrdenCreate, OrdenByEstado, OrdenPesajeInicial, OrdenPesajeFinal, Producto, OrdenCargaDatos, OrdenCierre, OrdenConciliacion, AlertaAlarma }
};

</script>

<style>

</style>


