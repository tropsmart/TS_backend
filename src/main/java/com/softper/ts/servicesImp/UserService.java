package com.softper.ts.servicesImp;

import com.softper.ts.exception.ResourceNotFoundException;
import com.softper.ts.models.*;
import com.softper.ts.repositories.IBlockRepository;
import com.softper.ts.repositories.IFavoriteRepository;
import com.softper.ts.repositories.IUserRepository;
import com.softper.ts.resources.comunications.BaseResponse;
import com.softper.ts.resources.outputs.*;
import com.softper.ts.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IFavoriteRepository favoriteRepository;

    @Autowired
    private IBlockRepository blockRepository;


    @Override
    public BaseResponse setFavourited(int userId, int favouriteId)
    {
        BaseResponse response = new BaseResponse();
        try
        {
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("id","User",userId));

            User getUserFavourite = userRepository.findById(favouriteId)
                    .orElseThrow(()->new ResourceNotFoundException("id","User",favouriteId));

           

            if(getUser.getPerson().getPersonType()==2 || getUserFavourite.getPerson().getPersonType()==1)
                return new BaseResponse("setFavourited","Solo los clientes pueden agregar a conductores como favoritos",0);

            Favorite getFavorite = favoriteRepository.findFavouriteByUserAndFavouriteId(userId, favouriteId);
            if(getFavorite != null){
                return new BaseResponse("setFavourited","Ya tiene a este usuario en su lista de favoritos",0);
            } else {
                Favorite newFavourite = new Favorite();
                newFavourite.setUser(getUser);
                newFavourite.setFavorited(getUserFavourite);
                newFavourite.setCreatedAt(new Date());
                newFavourite = favoriteRepository.save(newFavourite);
    
                response = new BaseResponse("setFavourited","Agregado a favoritos",1);
                response.setFavoriteOutput(toFavoriteOutput(newFavourite));
                return response;
            }        
        }
        catch (Exception e)
        {
            return new BaseResponse("setFavourited", "An error ocurred while saving the favourite: : "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse setBlocked(int userId, int blockedId) {
        BaseResponse response = new BaseResponse();
        try
        {
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("id","User",userId));

            User getUserBlocked = userRepository.findById(blockedId)
                    .orElseThrow(()->new ResourceNotFoundException("id","User",blockedId));

            Block getBlocked = blockRepository.findBlockByUserAndBlockedId(userId, blockedId);

            if(getBlocked != null){
                return new BaseResponse("setFavourited","Ya tiene a este usuario en su lista de bloqueados",0);
            } else {
                Block newBlock =  new Block();
                newBlock.setUser(getUser);
                newBlock.setBlocked(getUserBlocked);
                newBlock.setCreatedAt(new Date());
    
                newBlock = blockRepository.save(newBlock);
                response = new BaseResponse("setBlocked","Agregado a bloqueados",1);
                response.setBlockedOutput(toBlockedOutput(newBlock));
                return response;
            }

            
        }
        catch (Exception e)
        {
            return new BaseResponse("setBlocked", "An error ocurred while saving the favourite: : "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findAllUsers() {
        BaseResponse response = new BaseResponse();
        try
        {
            List<User> userList = userRepository.findAll();
            List<UserOutput> userOutputList = new ArrayList<>();
            for (User u:userList) {
                userOutputList.add(toUserOutput(u));
            }
            response = new BaseResponse("findAllUsers","success",1);
            response.setUserOutputList(userOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findAllUsers", "An error ocurred while saving the favourite: : "+e.getMessage(),-2);
        }
    }


    @Override
    public BaseResponse findFavoritesByUserId(int userId) {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Favorite> favoriteList = favoriteRepository.findFavouritesByUserId(userId);
            List<FavoriteOutput> favoriteOutputList = new ArrayList<>();
            for (Favorite f:favoriteList) {
                favoriteOutputList.add(toFavoriteOutput(f));
            }
            response = new BaseResponse("findFavoritesByUserId","success",1);
            response.setFavoriteOutputList(favoriteOutputList);
            return response;        
        }
        catch (Exception e)
        {
            return new BaseResponse("findFavoritesByUserId", "An error ocurred while saving the favourite: : "+e.getMessage(),-2);

        }
    }

    @Override
    public BaseResponse findBlockedsByUserId(int userId) {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Block> blockList = blockRepository.findBlockedsByUserId(userId);
            List<BlockedOutput> blockedOutputList = new ArrayList<>();
            for (Block b:blockList) {
                blockedOutputList.add(toBlockedOutput(b));
            }
            response = new BaseResponse("findBlockedsByUserId","success",1);
            response.setBlockedOutputList(blockedOutputList);
            return response;           
        }
        catch (Exception e)
        {
            return new BaseResponse("findBlockedsByUserId", "An error ocurred while saving the favourite: : "+e.getMessage(),-2);
        }

    }

    @Override
    public BaseResponse findAllFavourites() {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Favorite> favoriteList = favoriteRepository.findAll();
            List<FavoriteOutput> favoriteOutputList = new ArrayList<>();
            for (Favorite f:favoriteList) {
                favoriteOutputList.add(toFavoriteOutput(f));
            }
            response = new BaseResponse("findAllFavourites","success",1);
            response.setFavoriteOutputList(favoriteOutputList);
            return response;               
        }
        catch (Exception e)
        {
            return new BaseResponse("findAllFavourites", "An error ocurred while saving the favourite: : "+e.getMessage(),-2);

        }
    }

    @Override
    public BaseResponse findAllBlockeds() {
        BaseResponse response = new BaseResponse();
        try
        {
            List<Block> blockList = blockRepository.findAll();
            List<BlockedOutput> blockedOutputList = new ArrayList<>();
            for (Block b:blockList) {
                blockedOutputList.add(toBlockedOutput(b));
            }
            response = new BaseResponse("findAllBlockeds","success",1);
            response.setBlockedOutputList(blockedOutputList);
            return response;        
        }
        catch (Exception e)
        {
            return new BaseResponse("findAllBlockeds", "An error ocurred while saving the favourite: : "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findFavoriteByUserIdAndFavoriteId(int userId, int favouriteId) {
        BaseResponse response = new BaseResponse();
        try
        {
            Favorite getFavourite = favoriteRepository.findFavouriteByUserAndFavouriteId(userId, favouriteId);
            response = new BaseResponse("findFavoriteByUserIdAndFavoriteId","success",1);
            response.setFavoriteOutput(toFavoriteOutput(getFavourite));
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findFavoriteByUserIdAndFavoriteId", "An error ocurred while saving the favourite: : "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findBlockByUserIdAndBlockedId(int userId, int blockedId) {
        BaseResponse response = new BaseResponse();
        try
        {
            Block getBlock = blockRepository.findBlockByUserAndBlockedId(userId, blockedId);
            response = new BaseResponse("findBlockByUserIdAndBlockedId","success",1);
            response.setBlockedOutput(toBlockedOutput(getBlock));
            return response;        
        }
        catch (Exception e)
        {
            return new BaseResponse("findBlockByUserIdAndBlockedId", "An error ocurred while saving the favourite: : "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findAllUsersByType(int userType) {
        BaseResponse response = new BaseResponse();
        try{
            List<User> userList = userRepository.findAll();
            List<UserOutput> userOutputList = new ArrayList<>();
            for (User u:userList) {
                if(u.getPerson().getPersonType()==userType) {
                    userOutputList.add(toUserOutput(u));
                }

            }
            response = new BaseResponse("findAllUsersByType","success",1);
            response.setUserOutputList(userOutputList);
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findAllUsersByType", "An error ocurred while saving the favourite: : "+e.getMessage(),-2);
        }
    }


    @Override
    public BaseResponse deleteFavoriteByUserIdAndFavoriteId(int userId, int favoriteId) {
        BaseResponse response = new BaseResponse();
        try
        {
            Favorite getFavorite = favoriteRepository.findFavouriteByUserAndFavouriteId(userId, favoriteId);
            favoriteRepository.deleteById(getFavorite.getId());
            response = new BaseResponse("deleteFavoriteByUserIdAndFavoriteId","success",1);
            response.setFavoriteOutput(toFavoriteOutput(getFavorite));
            return response;        
        }
        catch (Exception e)
        {
            return new BaseResponse("deleteFavoriteByUserIdAndFavoriteId", "An error ocurred while saving the favourite: : "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse deleteBlockByUserIdAndBlockId(int userId, int blockedId) {
        BaseResponse response = new BaseResponse();
        try
        {
            Block getBlock = blockRepository.findBlockByUserAndBlockedId(userId, blockedId);
            blockRepository.deleteById(getBlock.getId());
            response = new BaseResponse("deleteBlockByUserIdAndBlockId","success",1);
            response.setBlockedOutput(toBlockedOutput(getBlock));
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("deleteBlockByUserIdAndBlockId", "An error ocurred while saving the favourite: : "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findUserByEmail(String email) {
        BaseResponse response = new BaseResponse();
        try
        {
            User getUser = userRepository.findPersonByEmail(email)
                    .orElseThrow(()->new ResourceNotFoundException("email","user",email));

            response = new BaseResponse("findUserByEmail","success",1);
            response.setUserOutput(toUserOutput(getUser));
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findUserByEmail", "An error ocurred while saving the favourite: : "+e.getMessage(),-2);
        }

    }

    @Override
    public BaseResponse findUserById(int userId) {
        BaseResponse response = new BaseResponse();
        try
        {
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
            response = new BaseResponse("findUserById","success",1);
            response.setUserOutput(toUserOutput(getUser));
            return response;        
        }
        catch (Exception e)
        {
            return new BaseResponse("findUserById", "An error ocurred while saving the favourite: : "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findCustomerByUserId(int userId) {
        BaseResponse response = new BaseResponse();
        try
        {
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("userId","user",userId));
            Customer getCustomer = getUser.getPerson().getCustomer();

            response = new BaseResponse("findCustomerByUserId","success",1);
            response.setCustomerOutput(new CustomerOutput(getCustomer.getPerson().getUser().getId(),getCustomer.getPerson().getFirstName(),getCustomer.getPerson().getLastName(),getCustomer.getCredits(),getCustomer.getPerson().getUser().getEmail(), getCustomer.getPerson().getPersonType(), getCustomer.getId()));
            return response;
        }
        catch (Exception e)
        {
            return new BaseResponse("findCustomerByUserId", "An error ocurred while saving the favourite: : "+e.getMessage(),-2);
        }
    }

    @Override
    public BaseResponse findDriverByUserId(int userId) {
        BaseResponse response = new BaseResponse();
        try
        {
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()-> new ResourceNotFoundException("Id","user",userId));

            Driver getDriver = getUser.getPerson().getDriver();

            response = new BaseResponse("findDriverByUserId","success",1);
            response.setDriverOutput(new DriverOutput(getDriver.getId(),getDriver.getPerson().getFirstName(),getDriver.getPerson().getLastName(),getDriver.getLicense(),getDriver.getPerson().getUser().getEmail(),getDriver.getPerson().getPersonType(), getDriver.getId()));
            return response;

        }
        catch (Exception e)
        {
            return new BaseResponse("findDriverByUserId", "An error ocurred while saving the favourite: : "+e.getMessage(),-2);

        }
    }


    @Override
    public User save(User user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        userRepository.deleteById(id);
    }


    @Override
    public Optional<User> findById(Integer id) throws Exception {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() throws Exception {
        return userRepository.findAll();
    }

    public UserOutput toUserOutput(User user){
        UserOutput newUserOutput = new UserOutput();
        newUserOutput.setId(user.getId());
        newUserOutput.setEmail(user.getEmail());
        newUserOutput.setFirstName(user.getPerson().getFirstName());
        newUserOutput.setLastName(user.getPerson().getLastName());
        newUserOutput.setPassword(user.getPassword());

        if(user.getPerson().getPersonType()==1)
            newUserOutput.setRole("Customer");
        else if(user.getPerson().getPersonType()==2)
            newUserOutput.setRole("Driver");

        if(user.getPerson().getPersonType()==1)
            newUserOutput.setRoleId(user.getPerson().getCustomer().getId());
        if(user.getPerson().getPersonType()==2)
            newUserOutput.setRoleId(user.getPerson().getDriver().getId());

        return newUserOutput;
    }

    public FavoriteOutput toFavoriteOutput(Favorite favorite)
    {
        FavoriteOutput newFavouriteOutput = new FavoriteOutput();
        newFavouriteOutput.setUser(favorite.getUser().getPerson().getFirstName()+" "+favorite.getUser().getPerson().getLastName());
        newFavouriteOutput.setFavourited(favorite.getFavorited().getPerson().getFirstName()+" "+favorite.getFavorited().getPerson().getLastName());
        newFavouriteOutput.setSince(favorite.getCreatedAt());
        return newFavouriteOutput;
    }

    public BlockedOutput toBlockedOutput(Block block)
    {
        BlockedOutput newBlockedOutput = new BlockedOutput();
        newBlockedOutput.setUser(block.getUser().getPerson().getFirstName()+" "+block.getUser().getPerson().getLastName());
        newBlockedOutput.setBlocked(block.getBlocked().getPerson().getFirstName()+" "+block.getBlocked().getPerson().getLastName());
        newBlockedOutput.setSince(block.getCreatedAt());
        return newBlockedOutput;
    }
}
