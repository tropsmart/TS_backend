package com.softper.ts.resources.comunications;

import com.softper.ts.resources.outputs.FavoriteOutput;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FavoriteResponse extends BaseResponse<FavoriteOutput>{
    public FavoriteResponse(List<FavoriteOutput> favouriteOutputList) {super(favouriteOutputList);}
    public FavoriteResponse(FavoriteOutput favouriteOutput) {super(favouriteOutput);}
    public FavoriteResponse(String message) {super(message);}
}
