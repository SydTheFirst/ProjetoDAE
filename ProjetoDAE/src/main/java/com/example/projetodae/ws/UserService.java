/*package pt.ipleiria.estg.dei.ei.dae.academics.ws;

@Path("api")
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
@Authenticated
@RolesAllowed({"Cliente", "Administrator", "Student"})

public class UserService {
    public enum TipoUser {
        Cliente,
        Logistica,
        Admin // Add more as needed
    }


    @EJB
    private UserBean userBean;

    //region GET
    @GET // means: to call this endpoint, we need to use the HTTP GET method
    @Path("/") // means: the relative url path is “/api/users/”
    public List<UserDTO> getAllUsers() {
        return DTOconverter.usersToDTOs(UserBean.getAll());
    }

    @GET
    @Path("{username}")
    public Response getUserDetails(@PathParam("username") String username) throws MyEntityNotFoundException {

        User user = UserBean.find(username);
        return Response.ok(DTOconverter.toDTO(user)).build();
    }


    @POST
    @Path("/")
    public Response createNewUser (UserDTO userDTO) throws MyEntityNotFoundException, MyEntityExistsException, MyConstraintViolationException {
        UserBean.create(
                userDTO.getId(),
                userDTO.Username(),
                userDTO.Password(),
                userDTO.tipoUser(),

        );

        User newUser = UserBean.find(UserDTO.getUsername());
        return Response.status(Response.Status.CREATED).entity(DTOconverter.toDTO(newUser)).build();
    }

    @PUT
    @Path("{username}")
    public Response updateUser(@PathParam("username") String username, UserDTO userDTO) {
        userBean.updateUser(username, userDTO.getId(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getTipoUser());
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{username}")
    public Response deleteUser(@PathParam("username") String username) {
        userBean.deleteUser(username);
        return Response.status(Response.Status.OK).build();
    }

}
*/