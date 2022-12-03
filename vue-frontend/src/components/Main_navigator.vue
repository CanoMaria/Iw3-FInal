<template>
  <div>
    <div>
      <!-- Aquí se cargará el componente seleccionado en la barra de navegación -->
      <Navbar @selectComponent="selectedComponent = $event" />
      <!-- <Camion v-if="selectedComponent === 'Camion'" />
      <Chofer v-if="selectedComponent === 'Chofer'" />
      <Orden v-if="selectedComponent === 'Orden'" /> -->

      <component :is="selectedComponent"></component>
      
    </div>
  </div>
</template>

<script>
import Navbar from './Navbar.vue';
import Camion from './Camion.vue';
import Orden from './Orden.vue';
import Chofer from './Chofer.vue';
import Login from './Login.vue';

export default {
  name: 'MainPage',
  components: {
    Navbar,
    Camion,
    Orden,
    Chofer,
    Login
  },
  data() {
    return {
      selectedComponent: '', // Aquí se almacenará el nombre del componente seleccionado
    };
  },
  mounted() {
    // Por defecto, se cargará el componente 1
     this.selectedComponent = 'Login';
  },
  methods: {
    // Esta función se llamará desde la barra de navegación para cambiar el componente cargado
    selectComponent(componentName) {
      this.selectedComponent = componentName;
    },
  },
};
</script>

<style>

</style>