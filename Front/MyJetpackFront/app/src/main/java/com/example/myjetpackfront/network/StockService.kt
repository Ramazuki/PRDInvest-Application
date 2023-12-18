package com.example.myjetpackfront.network

import com.example.myjetpackfront.Stocks
import com.example.myjetpackfront.Data
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface StockService {
//    @GET("get_stocks/{ticker}")
//    suspend fun stockSearch(
//        @Path("ticker") ticker: String,
//        @Query("max_results") maxResult: Int
//    ): Stocks
//}



    @GET("tmp_stocks")
    suspend fun stockSearch(): Stocks {
        return Stocks(
            arrayListOf(
                Data("AAPL", 123.45, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.facebook.com%2Fapple%2F&psig=AOvVaw1q9EgLvURd0llHWJE_jX2R&ust=1702855989768000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCPjAuImPlYMDFQAAAAAdAAAAABAF"),
                Data("TSLA", 67.89, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fpixabay.com%2Fillustrations%2Ftesla-logo-tesla-icon-7408969%2F&psig=AOvVaw3ML0wiUqgaqBEDd-42Ndhm&ust=1702856042610000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCPCZ9p6PlYMDFQAAAAAdAAAAABAI"),
                Data("INTC", 67.89, "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADgAAAA4CAMAAACfWMssAAAAbFBMVEX/uQF/ugABpO/yUCL/////tgAAou+s0Hb+47NpvfL936l8uAD1kn/yShb0iXLyTBrzc1bI4KnyaEe02/ZMtPCQwTgAnu73saP2pJTa6cSozm/yRQfzcFF1tQD1l4HyZELO47GLvyrI5fn+8tz9KGbWAAAAd0lEQVRIie3USxKCQBAE0RZLFHEGPygoyiDe/44Yrrs0gqVRb5/btGbHtMD1tmYsdsHX3YHDo2QshqWv2r/DcsEoVKhQ4d+HlS/8+Gqfjr4UgeF5ZgwzzQ/HEzMCrw1lxYWpge2KsiLPfPknNEqhQoUKFZKQ+R5OHIQwDedbxtgAAAAASUVORK5CYII="),
                Data("MSFT", 67.89, "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADgAAAAlCAMAAAAgENppAAAAZlBMVEX///8Ab8QAcMUAccUAasMAbMMAZ8KGrNsAYcD5+/3Q3vAAY8C90eoAZcETdcYAX7+ivuI4gct+p9mwx+Zrm9Tj6/YAWr7X4vLG1u1XkNCRst5Lic7w9fu3zOgAVbxhldJ2odeZuOByHMAUAAACkElEQVRIiY2V62KrIAyA5RLURlEQaxGd9v1fckF7VutRt/woSPOZEHNJknMZbk3vvNGCCxNc2twudH+kbRxUWUWimBAqyxCxxFAPl1TXCyKMrR/dP8WhG1ODSqFrz6i81vfK192R740pAe3xvewd58e5Mw8tpf7f32H+CsXVJUhsyeSerO/29AYbrZLp/VH+OxZtgkz/pPiSoSgoYjHOjGdvEwbKX+zdKgpolzbkmZTN6zAnED/BaZp2oII0GeqRbCNz6xkF2PnwCQqAA3AVxsyyhnDgmWb8FDRMxGVW9NPbOU+62UZ5DiuY0lEy9CHM3R6MH6T4ipliJEWqQJBKSZnR59Scq/uQFJmUEqrmA0QWfVRhfQlFtVAszHNg4BdwdnlbcUh74FW7AWlHCdtXtw0oa9rLeIX1jg4UWW8R5g04g6LYKp7sQf4Ggfk1UvoNtiiQ0v1VJsdgnglOd5Sc4xs0TPZJkuJ4BaIQnDG2BZ3kjBaP3ZWranV18x0nJbKI6LI9A2PmeBaDs6T2rSSw1SCyxUehVjDsQQ/gXf7IOPT1jGJI2ozARjFcG4RW6zpWlORFWUYQgC7RVSApAXpaSKK2q2wy3sOr/IN8rpuCkvzmfXRj8rEwWjv56KHT3PRLARRj8vjpLBb2feCPMkp10dQuJEcu/qxc15sHC3BUjkeYyLZgDhzM9UiI0lkluaw/jjLBfprPCdVrBE7D5/lx/EDGlXieWB3GWSDQyBKA065fdxyoCNCkY7ttWDSh+kmgjLbIKTUftHmLQP+BLJGZ4EmC0YqmGrAICZAYmuO2O1jSikqxiBZZCHqmLlROzVX0RidLRWm6IJxeAIsH7nBU7qR91HYiL4XWxjv7HLvTsfANUPQnf3A/mpMAAAAASUVORK5CYII=",),
                Data("NVDA", 67.89, "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADgAAAAfCAMAAACF8f6iAAAAkFBMVEX///8AAADt7e319fX5+fm2trZytwD8/PzN47ahoaGgzW7U1NSNjY1qtADx8fFutgDe7c+srKzk5OS8vLzC3aSYyV+cymX1+vC9257G4KujznWr0oPp896x1YuLwkaAvi2EhIRBQUHLy8vu9eZJSUkTExNwcHBdXV1frwBTU1OHwT6SxlSXl5e52ZZoaGjV6MFW2K89AAABDElEQVQ4je3RWXOCMBQF4HvIAohsCYhxQVBrKyr9//+uFzudlr7JM2cmD1m+mZOEaFq8iW6GM/xNUa4WnFX2Gqzc2pWx1joOSCaJIJI8SPjCl0L4RP5zKv7DzXa3r1110DoN6A6ODWGogYe84VnrhxB0BKIRPKVZsSjI1dQNMERkzkgQ0ttFQi1BEZqc4ful7Uew+qhi2pSdy9KhaghJOag9JziKJ6TrNYcUiBRv/a3qul29vWUPXdFhgIKhDHEHJd8QfQ5uu2y4xuiO63r32NaOslv8A4UH9ANs0PLduAZ6ZVskZvSqp33xGWx0nDI0yn8Oaw1JZSJrrccLnuLjRkVjOPkfX8wMZzgRfgHU6BAHXjx3gQAAAABJRU5ErkJggg==")
            )
        )
    }
}