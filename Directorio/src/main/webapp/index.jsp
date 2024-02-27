<%@page import= "com.mycompany.directorio.Directorio"%> 
<!DOCTYPE html>
<%@include file= "template/header.jsp" %>

<%
    Directorio tabla = new Directorio ();
    
    ServletContext context = getServletContext();
    String terminoBusqueda = request.getParameter("buscar");
    tabla = Directorio.cargarContacto(context);
    

    boolean contactosExiste = tabla.verificar();
    boolean contactoEncontrado = tabla.verificarExistencia(terminoBusqueda, request);
    boolean contaEncon = contactoEncontrado;
    String tablaHtml = Directorio.listarContactos(context, request, terminoBusqueda);
  System.out.println(tabla);
    %>


   <html lang="en">
 
    <body>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="index.jsp">Contactos</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                </div>
            </div>
        </nav>
        <!-- Page Header-->
        <header class="masthead" style="background-image: url('assets/img/home-bg.jpg')">
            <div class="container position-relative px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-md-10 col-lg-8 col-xl-7">
                        <div class="site-heading">
                            <h2>Directorio de contactos online</h2>
                            <span class="subheading">Bienvenido!</span>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- Main Content-->
        <div class="container px-4 px-lg-5">
            <div class="row gx-4 gx-lg-5 justify-content-center">
                <div class="col-md-10 col-lg-8 col-xl-7">
                    <!-- Post preview-->
                    <div class="post-preview">
                        <a href="contacto.jsp">
                            <h2 class="post-title">¿Aún no has agregado un contacto?</h2>
                            <h3 class="post-subtitle">Seleccione aqui para agregar</h3>
                        </a>
                   
                    </div>
                    <!-- Divider-->
                    <hr class="my-4" />
                    <!-- Post preview-->
                    <div class="post-preview">
                        <a><h2 class="post-title">Visualiza tus contactos</h2></a>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">id</th>
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% 
                                if (!contactosExiste) { %>
                                 <tr>
                            <td colspan='6' align='center' valign='middle'>No hay contactos</td>
                        </tr>
                                    <% }
                              else if(!contaEncon && terminoBusqueda!=null){ %>
                                 <tr>
                            <td colspan='6' align='center' valign='middle'>Contacto no encontrado </td>
                                 </tr>
                                   <% }  
                                %>
                                
                                
                                <%=tablaHtml%>
                            </tbody>
                        </table>

                    </div>
                    <!-- Divider-->
                    <hr class="my-4" />
                    <!-- Post preview-->
                    <div class="post-preview">
                        <a >
                            <h2 class="post-title">Busca un contacto</h2>
                        </a>
                        <nav class="navbar bg-body-tertiary">
                            <div class="container-fluid">
                                <form action="SvBuscar" method="Get" class="d-flex" role="search">
                                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="contact">
                                    <button class="btn btn-outline-success" type="submit">Search</button>
                                </form>
                            </div>
                        </nav>
                     
                    </div>
                    <!-- Divider-->
                    
                    </div>
                    <!-- Divider-->
                    <hr class="my-4" />
                    <!-- Pager-->
                </div>
            </div>
        </div>
        
             <!-- modal donde se muestran los  contactos -->

    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"> 
         <div class="modal-dialog"> 
             <div class="modal-content"> 
                 <div class="modal-header"> 
                    <h5 class="modal-title" id="exampleModalLabel">Detalles del Contacto</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> 
                 </div>
                 <div class="modal-body"> 
                  
                     <div id="contacto-details"> 
                         <!-- AquÃ­ se aÃ±ade los detalles del perro-->
                </div>
                 </div> 
                 <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button> 
                </div>
             </div> 
         </div> 
     </div>
             
             
                             <!-- Modal de confirmacion de la accion eliminar  -->           
<div class="modal fade" id="eliminarTareaModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="eliminarLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title" id="eliminarLabel">Eliminar Contacto</h2>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <h4>¿Estás seguro de que deseas eliminar este contacto?</h4>
                <p id="contactoNombre"></p>
                <form id="eliminarForm" action="SvEliminar" method="GET">
                    <input type="hidden" id="inputEliminar" name="inputEliminar">
                </form>
            </div>
            <div class="modal-footer justify-content-center">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <button type="button" class="btn btn-danger" onclick="eliminarContacto()">Eliminar</button>
            </div>
        </div>
    </div>
</div>


       
        
        <!-- Footer-->
        <footer class="border-top">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-md-10 col-lg-8 col-xl-7">
                        <ul class="list-inline text-center">
                            <li class="list-inline-item">
                                <a href="#!">
                                    <span class="fa-stack fa-lg">
                                        <i class="fas fa-circle fa-stack-2x"></i>
                                        <i class="fab fa-twitter fa-stack-1x fa-inverse"></i>
                                    </span>
                                </a>
                            </li>
                            <li class="list-inline-item">
                                <a href="#!">
                 
                                </a>
                            </li>
                            <li class="list-inline-item">
                                <a href="#!">
 
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        
        <script>
            
            // funcion para mostrar los datos en la ventana modal
  $('#exampleModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // BotÃ³n que desencadenÃ³ el evento
    var titulo = button.data('nombre'); // ObtÃ©n el nombre del contacto

    // Realiza una solicitud AJAX al servlet para obtener los detalles del perro por su nombre
    $.ajax({
      url: 'SvMostrar?id=' + titulo, // Cambia 'id' por el nombre del parÃ¡metro que esperas en tu servlet
      method: 'GET',
      success: function (data) {
        // Actualiza el contenido del modal con los detalles del perro
        $('#contacto-details').html(data);
      },
      error: function () {
        // Maneja errores aquÃ­ si es necesario y se imprime en consola
        console.log('Error al cargar los detalles del contacto.');
      }
    });
  });
        </script>
        
        <script>
   $('#eliminarTareaModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // Botón que desencadenó el evento
    var nombreContacto = button.data('nombre'); // Obtén el nombre del contacto

    // Mostrar el nombre del contacto en el párrafo dentro del modal de eliminación
    $('#contactoNombre').text('Nombre del contacto: ' + nombreContacto);

    // Establecer el valor del campo oculto con el nombre del contacto
    $('#inputEliminar').val(nombreContacto);
});

function eliminarContacto() {
    $('#eliminarForm').submit(); // Enviar el formulario al servlet
}

        </script>
    </body>
</html>

    