package com.softper.ts.servicesImp;

import com.google.common.base.Strings;
import com.softper.ts.config.DataUtils;
import com.softper.ts.exception.ResourceNotFoundException;
import com.softper.ts.models.*;
import com.softper.ts.repositories.*;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.inputs.RefreshInput;
import com.softper.ts.resources.inputs.SignUp;
import com.softper.ts.resources.outputs.AuthenticatedOutput;
import com.softper.ts.security.JwtProvider;
import com.softper.ts.services.IAuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.validator.cfg.GenericConstraintDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;



@Service
public class AuthService implements IAuthService {

	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);


    @Autowired
    private IPersonRepository personRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private IDriverRepository driverRepository;

    @Autowired
    private IQualificationRepository qualificationRepository;

    @Autowired
    private IServiceRequestRepository serviceRequestRepository;

    @Autowired
    private IConfigurationRepository configurationRepository;

    @Autowired
    private IBalanceRepository balanceRepository;

    @Autowired
    private ISoatRepository soatRepository;

    @Autowired
    private IVehicleRepository vehicleRepository;

    @Autowired
    private IDriverLocationRepository driverLocationRepository;

    //@Autowired
    //private AuthenticationManager authenticationManager;

    DataUtils data;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Autowired
    private JwtProvider jwtProvider;

    public AuthService() {
        try{
            data = new DataUtils();
        } catch(IOException e) {

        }
    }

    //@Autowired
    //private IRefreshTokenService refreshTokenService;

    //@Autowired
    //private PasswordEncoder encoder;



    @Override
    public BaseResponse registerComplete(SignUp signUp) {
        BaseResponse response = new BaseResponse();
        try
        {
            logger.info("signUp");
            Optional<User> result = userRepository.findByEmail(signUp.getEmail());
            if(result.isPresent()) {
                response = new BaseResponse("registerComplete", "El correo ya se encuentra registrado", 0);
                logger.info("Correo registrado : "+result.get().getEmail());
                return response;
            } else {
                logger.info("Correo no registrado");
                
                Person newPerson = new Person();
                newPerson.setFirstName(signUp.getFirstName());
                newPerson.setLastName(signUp.getLastName());
                newPerson.setPhone(signUp.getPhone());
                newPerson.setPersonType(signUp.getDiscriminator());
                newPerson = personRepository.save(newPerson);


                Configuration newConfiguration = new Configuration();
                newConfiguration.setLanguage("Spanish");
                newConfiguration.setPaymentCurrency("Soles");
    
                newConfiguration = configurationRepository.save(newConfiguration);
    
                Balance newBalance = new Balance();
                newBalance.setSpentMoney(0);
                newBalance.setAddedMoney(0);
    
                newBalance = balanceRepository.save(newBalance);
    
    
                User user = new User();
                user.setEmail(signUp.getEmail());
                user.setPerson(newPerson);
    
                //user.setPassword(encoder.encode((signUp.getPassword())));
                user.setPassword(signUp.getPassword());
                user.setCreatedAt(Calendar.getInstance().getTime());
                user.setConfiguration(newConfiguration);
                user.setBalance(newBalance);
    
                user = userRepository.save(user);
    
    
                if(signUp.getDiscriminator() == 1) {
                    Customer newCustomer = new Customer();
                    newCustomer.setCredits(0.0);
                    newCustomer.setPerson(newPerson);
                    newPerson.setCustomer(newCustomer);
                    customerRepository.save(newCustomer);
                }
                else
                {
                    Driver newDriver = new Driver();
                    newDriver.setLicense("000-123");
                    newDriver.setPerson(newPerson);
                    newPerson.setDriver(newDriver);
    
                    Qualification newQualication = new Qualification();
                    newQualication.setDriver(newDriver);
    
                    ServiceRequest newServiceRequest = new ServiceRequest();
                    newServiceRequest.setDriver(newDriver);
    
                    qualificationRepository.save(newQualication);
                    serviceRequestRepository.save(newServiceRequest);
                    driverRepository.save(newDriver);

                    //SOAT
                    Soat newSoat = new Soat();
                    newSoat.setAssociateName(newDriver.getPerson().getFirstName()+" "+newDriver.getPerson().getLastName());
                    newSoat.setEmissionDate(Calendar.getInstance().getTime());
                    newSoat.setExpireDate(Calendar.getInstance().getTime());
                    newSoat.setServiceType("Servicio de carga");

                    newSoat = soatRepository.save(newSoat);

                    //VEHICLE
                    Random rand = new Random();
                    int n = rand.nextInt(4)+1;
                    List<Vehicle> newVehicleList = new ArrayList();

                    for (int p = 0; p<n; p++){
                        Vehicle newVehicle = new Vehicle();
                        String vstr = data.getData().getVehicles().get(rand.nextInt(data.getData().getVehicles().size()-1));
                        newVehicle.setBrand(vstr);
                        newVehicle.setLoadingCapacity(105.5);
                        newVehicle.setModel(vstr);
                        newVehicle.setFabricationYear(Calendar.getInstance().getTime());
                        newVehicle.setOwnershipCard(vstr);
                        if(p == 0)
                            newVehicle.setState("Activado");
                        else
                            newVehicle.setState("Desactivado");
                        newVehicle.setDriver(newDriver);
                        newVehicle.setSoat(newSoat);
                        newVehicleList.add(newVehicle);
                    }
                    
                    int m = rand.nextInt(4)+1;
                    List<DriverLocation> driverLocationList = new ArrayList();
                    for (int r = 0 ; r<m; r++){
                        DriverLocation newDriverLocation = new DriverLocation();
                        newDriverLocation.setDriver(newDriver);
                        newDriverLocation.setLocation(data.getData().getLocations().get(rand.nextInt(data.getData().getLocations().size()-1)));
                        driverLocationList.add(newDriverLocation);
                    }
                    vehicleRepository.saveAll(newVehicleList);
                    driverLocationRepository.saveAll(driverLocationList);
                }
    
                response = new BaseResponse("registerComplete", "success", 1);
                response.setAuthenticatedOutput(new AuthenticatedOutput(user.getId(),user.getEmail(),user.getPassword(),signUp.getFirstName(),signUp.getLastName(),signUp.getDiscriminator()));
                return response;
            }
            //NestedFactory nestedFactory = new NestedFactory();
            //User user = (User)(nestedFactory.create(signUp.getDiscriminator(), signUp));


           }
        catch (Exception e)
        {
            return new BaseResponse("registerComplete", "Ocurrio un error : "+e.getMessage(), -2);
        }
    }

    @Override
    public BaseResponse login(String email, String password) {
        BaseResponse response = new BaseResponse();
        try {
            User getUser = userRepository.findByEmail(email)
                    .orElseThrow(()->new ResourceNotFoundException("user","email",email));
            Person getPerson = getUser.getPerson();


            //Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            //SecurityContextHolder.getContext().setAuthentication(authentication);

            AuthenticatedOutput authenticatedOutput =new AuthenticatedOutput(getUser.getId(),
                    getUser.getEmail(),
                    getUser.getPassword(),
                    getPerson.getFirstName(),
                    getPerson.getLastName(),
                    getPerson.getPersonType());

            if(getPerson.getPersonType()==1)
                authenticatedOutput.setRoleId(getPerson.getCustomer().getId());
            if(getPerson.getPersonType()==2)
                authenticatedOutput.setRoleId(getPerson.getDriver().getId());

            //String jwt = jwtProvider.generateJwtToken(authentication);
            //authenticatedOutput.setJwt(jwt);
            //authenticatedOutput.setRefreshToken(jwtProvider.generateRefreshToken(getUser, jwt, refreshTokenService));


            String secretKey = "mySecretKey";
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_USER");

            String token = Jwts
                    .builder()
                    .setId("softtekJWT")
                    .setSubject(email)
                    .claim("authorities",
                            grantedAuthorities.stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList()))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 600000))
                    .signWith(SignatureAlgorithm.HS512,
                            secretKey.getBytes()).compact();

            String r = "Bearer "+token;
            authenticatedOutput.setToken(r);
            response = new BaseResponse("login","success",1);
            response.setAuthenticatedOutput(authenticatedOutput);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("login","An error ocurred while getting the user: "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse loginFixed(String email, String password) {
        BaseResponse response = new BaseResponse();
        try {
            User getUser = userRepository.findByEmail(email)
                    .orElseThrow(()->new ResourceNotFoundException("user","email",email));
            if(getUser.getPassword().equals(password)){
                Person getPerson = getUser.getPerson();
                AuthenticatedOutput authenticatedOutput =new AuthenticatedOutput(
                        getUser.getEmail());
                int roleId=0;
                if(getPerson.getPersonType()==1)
                    roleId = getPerson.getCustomer().getId();
                if(getPerson.getPersonType()==2)
                    roleId = getPerson.getDriver().getId();

                String secretKey = "mySecretKey";
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList("ROLE_USER");

                String token = Jwts.builder().setId("softtekJWT").setSubject(authenticatedOutput.getEmail())
                        .claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .claim("id", getUser.getId())
                        .claim("email", getUser.getEmail())
                        .claim("firstName", getPerson.getFirstName())
                        .claim("lastName", getPerson.getLastName())
                        .claim("role", getPerson.getPersonType())
                        .claim("roleId", roleId)
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 600000))
                        .signWith(SignatureAlgorithm.HS512,
                                secretKey.getBytes()).compact();

                String r = "Bearer "+token;
                authenticatedOutput.setToken(r);
                response = new BaseResponse("loginfixed","success",1);
                response.setAuthenticatedOutput(authenticatedOutput);
                return response;
            }
            else {
                return new BaseResponse("loginfixed","Correo o contraseña incorrectos",0);
            }
            
        }
        catch (Exception e)
        {
            return new BaseResponse("loginfixed","Ocurrio un error : "+e.getMessage(),-2);
        }
    }

    /*
    @Override
    public BaseResponse refresh(RefreshInput refreshInput) throws Exception {
        RefreshToken refreshToken = refreshTokenService.findById(refreshInput.getRefreshToken()).get();
        String tokenId = jwtProvider.getJwtTokenId(refreshInput.getToken());

        if (refreshToken.getToken().equals(tokenId) && jwtProvider.validateRefreshToken(refreshToken)) {
            User user = refreshToken.getUser();
            refreshToken.setState(false);
            refreshTokenService.save(refreshToken);
            return login(user.getEmail(), user.getPassword());
        }
        return new BaseResponse("Can't validate token");
    }
     */
}