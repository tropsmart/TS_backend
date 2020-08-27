package com.softper.ts.servicesImp;

import com.softper.ts.exception.ResourceNotFoundException;
import com.softper.ts.models.Block;
import com.softper.ts.models.Favorite;
import com.softper.ts.models.User;
import com.softper.ts.repositories.IBlockRepository;
import com.softper.ts.repositories.IFavoriteRepository;
import com.softper.ts.repositories.IUserRepository;
import com.softper.ts.resources.comunications.BlockedResponse;
import com.softper.ts.resources.comunications.FavoriteResponse;
import com.softper.ts.resources.comunications.UserResponse;
import com.softper.ts.resources.outputs.BlockedOutput;
import com.softper.ts.resources.outputs.FavoriteOutput;
import com.softper.ts.resources.outputs.UserOutput;
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
    private IFavoriteRepository favouriteRepository;

    @Autowired
    private IBlockRepository blockRepository;


    @Override
    public FavoriteResponse setFavourited(int userId, int favouriteId)
    {
        try
        {
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("id","User",userId));

            User getUserFavourite = userRepository.findById(favouriteId)
                    .orElseThrow(()->new ResourceNotFoundException("id","User",favouriteId));

            if(getUser.getPerson().getPersonType()!=1 || getUserFavourite.getPerson().getPersonType()!=2)
                return new FavoriteResponse("Only customers can add driver as favourite");

            Favorite newFavourite = new Favorite();
            newFavourite.setUser(getUser);
            newFavourite.setFavorited(getUserFavourite);
            newFavourite.setCreatedAt(new Date());

            newFavourite = favouriteRepository.save(newFavourite);

            FavoriteOutput newFavouriteOutput = new FavoriteOutput();
            newFavouriteOutput.setUser(newFavourite.getUser().getPerson().getFirstName()+" "+newFavourite.getUser().getPerson().getLastName());
            newFavouriteOutput.setFavourited(newFavourite.getFavorited().getPerson().getFirstName()+" "+newFavourite.getFavorited().getPerson().getLastName());
            newFavouriteOutput.setSince(newFavourite.getCreatedAt());
            return new FavoriteResponse(newFavouriteOutput);
        }
        catch (Exception e)
        {
            return new FavoriteResponse("An error ocurred while saving the favourite: : "+e.getMessage());
        }
    }

    @Override
    public BlockedResponse setBlocked(int userId, int blockedId) {
        try
        {
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("id","User",userId));

            User getUserBlocked = userRepository.findById(blockedId)
                    .orElseThrow(()->new ResourceNotFoundException("id","User",blockedId));

            Block newBlock =  new Block();
            newBlock.setUser(getUser);
            newBlock.setBlocked(getUserBlocked);
            newBlock.setCreatedAt(new Date());

            newBlock = blockRepository.save(newBlock);

            BlockedOutput newBlockedOutput = new BlockedOutput();
            newBlockedOutput.setUser(newBlock.getUser().getPerson().getFirstName()+" "+newBlock.getUser().getPerson().getLastName());
            newBlockedOutput.setBlocked(newBlock.getBlocked().getPerson().getFirstName()+" "+newBlock.getBlocked().getPerson().getLastName());
            newBlockedOutput.setSince(newBlock.getCreatedAt());
            return new BlockedResponse(newBlockedOutput);
        }
        catch (Exception e)
        {
            return new BlockedResponse("An error ocurred while saving the blocked: : "+e.getMessage());
        }
    }

    @Override
    public UserResponse findAllUsers() {
        try
        {
            List<User> userList = userRepository.findAll();
            List<UserOutput> userOutputList = new ArrayList<>();
            for (User u:userList) {
                UserOutput newUserOutput = new UserOutput();
                newUserOutput.setEmail(u.getEmail());
                newUserOutput.setFirstName(u.getPerson().getFirstName());
                newUserOutput.setLastName(u.getPerson().getLastName());
                newUserOutput.setPassword(u.getPassword());

                if(u.getPerson().getPersonType()==1)
                    newUserOutput.setRole("Customer");
                else if(u.getPerson().getPersonType()==2)
                    newUserOutput.setRole("Driver");

                userOutputList.add(newUserOutput);
            }
            return new UserResponse(userOutputList);
        }
        catch (Exception e)
        {
            return new UserResponse("An error ocurred while getting the user list : "+e.getMessage());
        }
    }

    @Override
    public UserResponse findAllUsersDrivers() {
        return null;
    }

    @Override
    public FavoriteResponse findFavouritesByUserId(int userId) {
        try
        {
            List<Favorite> favoriteList = favouriteRepository.findFavouritesByUserId(userId);
            List<FavoriteOutput> favouriteOutputList = new ArrayList<>();
            for (Favorite f:favoriteList) {
                FavoriteOutput newFavouriteOutput = new FavoriteOutput();
                newFavouriteOutput.setUser(f.getUser().getPerson().getFirstName()+" "+f.getUser().getPerson().getLastName());
                newFavouriteOutput.setFavourited(f.getFavorited().getPerson().getFirstName()+" "+f.getFavorited().getPerson().getLastName());
                newFavouriteOutput.setSince(f.getCreatedAt());
                favouriteOutputList.add(newFavouriteOutput);
            }
            return new FavoriteResponse(favouriteOutputList);
        }
        catch (Exception e)
        {
            return new FavoriteResponse("An error ocurred while getting the favourite list : "+e.getMessage());

        }
    }

    @Override
    public BlockedResponse findBlockedsByUserId(int userId) {
        try
        {
            List<Block> blockList = blockRepository.findBlockedsByUserId(userId);
            List<BlockedOutput> blockedOutputList = new ArrayList<>();
            for (Block b:blockList) {
                BlockedOutput newBlockedOutput = new BlockedOutput();
                newBlockedOutput.setUser(b.getUser().getPerson().getFirstName()+" "+b.getUser().getPerson().getLastName());
                newBlockedOutput.setBlocked(b.getBlocked().getPerson().getFirstName()+" "+b.getBlocked().getPerson().getLastName());
                newBlockedOutput.setSince(b.getCreatedAt());
                blockedOutputList.add(newBlockedOutput);
            }
            return new BlockedResponse(blockedOutputList);
        }
        catch (Exception e)
        {
            return new BlockedResponse("An error ocurred while getting the blocked list : "+e.getMessage());
        }

    }

    @Override
    public FavoriteResponse findAllFavourites() {
        try
        {
            List<Favorite> favoriteList = favouriteRepository.findAll();
            List<FavoriteOutput> favoriteOutputList = new ArrayList<>();
            for (Favorite f:favoriteList) {
                FavoriteOutput newFavouriteOutput = new FavoriteOutput();
                newFavouriteOutput.setUser(f.getUser().getPerson().getFirstName()+" "+f.getUser().getPerson().getLastName());
                newFavouriteOutput.setFavourited(f.getFavorited().getPerson().getFirstName()+" "+f.getFavorited().getPerson().getLastName());
                newFavouriteOutput.setSince(f.getCreatedAt());
                favoriteOutputList.add(newFavouriteOutput);
            }
            return new FavoriteResponse(favoriteOutputList);
        }
        catch (Exception e)
        {
            return new FavoriteResponse("An error ocurred while getting the favourite list : "+e.getMessage());

        }
    }

    @Override
    public BlockedResponse findAllBlockeds() {
        try
        {
            List<Block> blockList = blockRepository.findAll();
            List<BlockedOutput> blockedOutputList = new ArrayList<>();
            for (Block b:blockList) {
                BlockedOutput newBlockedOutput = new BlockedOutput();
                newBlockedOutput.setUser(b.getUser().getPerson().getFirstName()+" "+b.getUser().getPerson().getLastName());
                newBlockedOutput.setBlocked(b.getBlocked().getPerson().getFirstName()+" "+b.getBlocked().getPerson().getLastName());
                newBlockedOutput.setSince(b.getCreatedAt());
                blockedOutputList.add(newBlockedOutput);
            }
            return new BlockedResponse(blockedOutputList);
        }
        catch (Exception e)
        {
            return new BlockedResponse("An error ocurred while getting the blocked list : "+e.getMessage());
        }
    }

    @Override
    public FavoriteResponse findFavouriteByUserIdAndFavouriteId(int userId, int favouriteId) {
        try
        {
            Favorite getFavourite = favouriteRepository.findFavouriteByUserAndFavouriteId(userId, favouriteId);
            FavoriteOutput newFavouriteOutput = new FavoriteOutput();
            newFavouriteOutput.setUser(getFavourite.getUser().getPerson().getFirstName()+" "+getFavourite.getUser().getPerson().getLastName());
            newFavouriteOutput.setFavourited(getFavourite.getFavorited().getPerson().getFirstName()+" "+getFavourite.getFavorited().getPerson().getLastName());
            newFavouriteOutput.setSince(getFavourite.getCreatedAt());
            return new FavoriteResponse(new FavoriteOutput());
        }
        catch (Exception e)
        {
            return new FavoriteResponse("An error ocurred while getting the favourite relation : "+e.getMessage());
        }
    }

    @Override
    public BlockedResponse findBlockByUserIdAndBlockedId(int userId, int blockedId) {
        try
        {
            Block getBlock = blockRepository.findBlockByUserAndBlockedId(userId, blockedId);
            BlockedOutput newBlockedOutput = new BlockedOutput();
            newBlockedOutput.setUser(getBlock.getUser().getPerson().getFirstName()+" "+getBlock.getUser().getPerson().getLastName());
            newBlockedOutput.setBlocked(getBlock.getBlocked().getPerson().getFirstName()+" "+getBlock.getBlocked().getPerson().getLastName());
            newBlockedOutput.setSince(getBlock.getCreatedAt());
            return new BlockedResponse(newBlockedOutput);
        }
        catch (Exception e)
        {
            return new BlockedResponse("An error ocurred while getting the blocked relation : "+e.getMessage());
        }
    }

    @Override
    public UserResponse findByEmail(String email) {
        try
        {
            User getUser = userRepository.findPersonByEmail(email)
                    .orElseThrow(()->new ResourceNotFoundException("email","user",email));

            UserOutput newUserOutput = new UserOutput();
            newUserOutput.setEmail(getUser.getEmail());
            newUserOutput.setFirstName(getUser.getPerson().getFirstName());
            newUserOutput.setLastName(getUser.getPerson().getLastName());
            newUserOutput.setPassword(getUser.getPassword());

            if(getUser.getPerson().getPersonType()==1)
                newUserOutput.setRole("Customer");
            else if(getUser.getPerson().getPersonType()==2)
                newUserOutput.setRole("Driver");

            return new UserResponse(newUserOutput);
        }
        catch (Exception e)
        {
            return new UserResponse("An error ocurred while getting the user : "+e.getMessage());
        }

    }

    @Override
    public UserResponse findUserById(int userId) {
        try
        {
            User getUser = userRepository.findById(userId)
                    .orElseThrow(()->new ResourceNotFoundException("User","id",userId));
            UserOutput newUserOutput = new UserOutput();
            newUserOutput.setEmail(getUser.getEmail());
            newUserOutput.setFirstName(getUser.getPerson().getFirstName());
            newUserOutput.setLastName(getUser.getPerson().getLastName());
            newUserOutput.setPassword(getUser.getPassword());

            if(getUser.getPerson().getPersonType()==1)
                newUserOutput.setRole("Customer");
            else if(getUser.getPerson().getPersonType()==2)
                newUserOutput.setRole("Driver");

            return new UserResponse(newUserOutput);

        }
        catch (Exception e)
        {
            return new UserResponse("An error ocurred while updating the user"+e.getMessage());
        }
    }

    @Override
    public FavoriteResponse findFavouritesByActiveUser(int userId) {
        return null;
    }

    @Override
    public FavoriteResponse findFavouritesByPassiveUser(int userId) {
        return null;
    }

    @Override
    public BlockedResponse findBlockedsByActiveUser(int userId) {
        return null;
    }

    @Override
    public BlockedResponse findBlockedsByPassiveUser(int userId) {
        return null;
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
}
