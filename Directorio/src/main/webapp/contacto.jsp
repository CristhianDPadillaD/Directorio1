<link rel="stylesheet" href="template/Contacto.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<section class="vh-100 bg-image"
  style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
  <div class="mask d-flex align-items-center h-100 gradient-custom-3">
    <div class="container h-100">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-12 col-md-9 col-lg-7 col-xl-6">
          <div class="card" style="border-radius: 15px;">
            <div class="card-body p-5">
              <h2 class="text-uppercase text-center mb-5">Agregar un contacto</h2>

              <form action="SvAgregar" method="GET" >

                <div class="form-outline mb-4">
                  <input type="text" id="form3Example1cg" class="form-control form-control-lg" name="nombre" required/>
                  <label class="form-label" for="form3Example1cg">nombre</label>
                </div>

                <div class="form-outline mb-4">
                  <input type="email" id="form3Example3cg" class="form-control form-control-lg" name="apellido" required />
                  <label class="form-label" for="form3Example3cg">apellido</label>
                </div>

                <div class="form-outline mb-4">
                  <input type="password" id="form3Example4cg" class="form-control form-control-lg" name="correo" required/>
                  <label class="form-label" for="form3Example4cg">correo</label>
                </div>

                <div class="form-outline mb-4">
                  <input type="password" id="form3Example4cdg" class="form-control form-control-lg" name=" direccion" required/>
                  <label class="form-label" for="form3Example4cdg">direccion</label>
                </div>
                  
                <div class="form-outline mb-4">
                  <input type="number" id="form3Example4cdg" class="form-control form-control-lg" name="celular" required />
                  <label class="form-label" for="form3Example4cdg">celular</label>
                </div>              

                  <div >
                <div class="d-flex justify-content-center"  >
                    <button type="submit"class="btn btn-success btn-block btn-lg gradient-custom-4 text-body">Agregar</button>
                </div>
                      
           
            </div>
              </form>    
                  
          </div>
        </div>
      </div>
    </div>
  </div>
</section>